package com.king.mobile.downloadlib;

import android.content.Context;
import android.os.Environment;

import androidx.lifecycle.LiveData;

import com.king.mobile.downloadlib.DownloadWorker.Listener;
import com.king.mobile.downloadlib.model.Task;
import com.king.mobile.downloadlib.model.ThreadTask;
import com.king.mobile.util.Executor;
import com.king.mobile.util.Loker;
import com.king.mobile.util.ToastUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadManager implements Listener {
    private Context mContext;
    private ExecutorService downloadExecutor;
    private static DownloadManager mInstance;

    private Announcer announcer;
    private Repository recorder;
    private Map<String, List<ThreadTask>> tTasksMap; // 正在下载的任务
    private List<Task> waitingList; // 等待的任务队列

    private float THREAD_TASK_MAX_SIZE = 10 * 1024 * 1024f;

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }

    private int taskCount = 3;
    private String downloadDir;

    private DownloadManager(Context context) {
        mContext = context;
        recorder = Repository.getInstance(context);
        waitingList = recorder.getWaitingTask();
        tTasksMap = recorder.getThreadTasksMap();
        if (!tTasksMap.isEmpty()) {
            start();
        }
        downloadDir = context.getExternalFilesDir(Environment.DIRECTORY_MOVIES).getAbsolutePath() + "/download";
        Loker.d("DOWNLOAD_DIR=", downloadDir);
    }

    public static DownloadManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DownloadManager.class) {
                if (mInstance == null) {
                    mInstance = new DownloadManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 创建下载任务
     *
     * @param url
     * @return
     */
    private synchronized Task createTaskSync(String url) {
        Loker.d("createTask", "url =", url);
        int lastIndexOf = url.lastIndexOf('.');
        String type = url.substring(lastIndexOf + 1);
        String fileName = String.format("%s.%s", UUID.randomUUID(), type);
        String path = String.format("%s/%s", downloadDir, fileName);
        Task task = new Task();
        task.url = url;
        task.name = fileName;
        task.path = path;
        task.size = 0;
        task.state = TaskState.STATE_CREATED;
        task.progress = 0f;
        task.completedSize = 0;
        task.createdAt = System.currentTimeMillis();
        task.type = type;
        task.priority = 0;
        task.md5 = "";
        try {
            Response response = OkHttpHelper.doRequest(url);
            ResponseBody body = response.body();
            if (response.code() == 200) {
                assert body != null;
                task.size = body.contentLength();
                task.state = TaskState.STATE_WAITING;
                File dir = new File(downloadDir);
                if (!dir.exists()) {
                    Loker.d("create download dir =============" + dir.mkdirs());
                }
            } else {
                task.state = TaskState.STATE_URL_ERROR;
            }
        } catch (IOException e) {
            task.state = TaskState.STATE_URL_ERROR;
            e.printStackTrace();
        }
        return task;
    }

    /**
     * 获取所有任务
     */
    LiveData<List<Task>> getAllTasks() {
        return null;
    }

    /**
     * 获取正在下载任务
     */
    LiveData<List<Task>> getRunningTasks() {
        return null;
    }

    private ExecutorService getExecutor() {
        if (downloadExecutor == null) {
            downloadExecutor = Executors.newFixedThreadPool(5);
        }
        return downloadExecutor;
    }

    /**
     * 开始某个任务
     */
//    void start(final Task task) {
//        //首先判断 任务是否已存在于下载列表
//        if (tTasksMap.containsKey(task.url)) { // 如果存在则
//        } else if (tTasksMap.size() < taskCount) { // 正在下载数还未满
//            Executor.getInstance().execute(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Response response = OkHttpHelper.doRequest(task.url);
//                        ResponseBody body = response.body();
//                        if (response.code() == 200) {
//                            assert body != null;
//                            task.size = body.contentLength();
//                            File dir = new File(downloadDir);
//                            if (!dir.exists()) dir.mkdir();
//                            createThreadTasks(task);
//                        } else {
//                            task.state = -1;
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            });
//        } else { // 添加到等待队列
//            waitingList.add(task);
//        }
//
//    }
    private synchronized void createThreadTasks(Task task) {
        List<ThreadTask> tTasks;
        int taskCount = (int) Math.ceil((task.size / THREAD_TASK_MAX_SIZE));
        tTasks = new ArrayList<>(taskCount);
        for (int i = 0; i < taskCount; i++) {
            long size;
            if (i == taskCount - 1) {
                size = (long) (task.size - THREAD_TASK_MAX_SIZE * i);
            } else {
                size = (long) THREAD_TASK_MAX_SIZE;
            }
            ThreadTask threadTask = new ThreadTask();
            threadTask.url = task.url;
            threadTask.size = size;
            threadTask.start = (long) (THREAD_TASK_MAX_SIZE * i);
            threadTask.finished = 0;
            threadTask.createAt = System.currentTimeMillis() / 1000;
            threadTask.isFinished = false;
            tTasks.add(threadTask);
            recorder.addThreadTask(threadTask);
        }
    }

    private void executeThreadTask(List<ThreadTask> tTasks, Task task) {
//        for (ThreadTask next : tTasks) {
//            if (!next.isFinished) {
//                DownloadWorker worker = new DownloadWorker(next, task);
//                worker.setListener(this);
//                getExecutor().execute(worker);
//            }
//        }

    }

    /**
     * 立即开始某个任务
     */
    public void startNow(Task task) {
        List<ThreadTask> threadTasks = tTasksMap.get(task.url);
        executeThreadTask(threadTasks, task);
    }

    /**
     * 开始下载列表
     */
    public void start() {
        Iterator<Map.Entry<String, List<ThreadTask>>> iterator = tTasksMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<ThreadTask>> next = iterator.next();
            Task task = recorder.getTaskByUrl(next.getKey());
            List<ThreadTask> value = next.getValue();
            executeThreadTask(value, task);
        }
    }

    /**
     * 停止所有任务
     */
    public void stop() {

    }

    /**
     * 停止任务
     */
    public void stop(Task task) {

    }

    /**
     * 删除任务
     */
    public void delete(Task task) {

    }


    public void download(String url) {
        if (recorder.idTaskExist(url)) { // 任务已存在
            Loker.d("--------任务已存在");
            Task task = recorder.getTaskByUrl(url);
            switch (task.state) {
                case TaskState.STATE_URL_ERROR:
//                    ToastUtil.show("下载地址有误");
                    Loker.d("下载地址有误");
                    break;
//                case TaskState.STATE_CREATED: // 只是
//                    createThreadTasks(task);
//                    break;
                case TaskState.STATE_WAITING:
                    Loker.d("下载等待");
                    if (tTasksMap.size() < taskCount) {
                        startNow(task);
                    }
                    break;
                case TaskState.STATE_DOWNLOADING:
                    Loker.d("正在下载中");
//                    ToastUtil.show("正在下载中");
                    break;
                case TaskState.STATE_PAUSE:
                    if (tTasksMap.size() < taskCount) {
                        startNow(task);
                    } else {
                        Loker.d("任务数已到上限，暂停其他任务后再试");
//                        ToastUtil.show("任务数已到上限，暂停其他任务后再试");
                    }
                    break;
                case TaskState.STATE_DOWNLOAD_ERROR:
                    Loker.d("下载出错，暂停其他任务后再试");
                    if (tTasksMap.size() < taskCount) {
                        task.state = TaskState.STATE_PAUSE;
                        recorder.updateTask(task);
                        startNow(task);
                    } else {
                        Loker.d("下载出错，暂停其他任务后再试");
//                        ToastUtil.show("下载出错，暂停其他任务后再试");
                    }
                    break;
                case TaskState.STATE_FINISHED:
                    Loker.d("已下载完成，去下载历史中查看");
//                    ToastUtil.show("已下载完成，去下载历史中查看");
                    break;
            }
        } else { // 新任务
            Loker.d("--------新任务");
            Task task = createTaskSync(url);
            recorder.addTask(task);
            task = recorder.getTaskByUrl(url);
            if (tTasksMap.size() == taskCount) { // 等待下载
                waitingList.add(task);
            } else { // 立即下载
                createThreadTasks(task);
                List<ThreadTask> threadTasks = recorder.getThreadTasks(url);
                tTasksMap.put(task.url, threadTasks);
                executeThreadTask(threadTasks, task);
                task.state = TaskState.STATE_DOWNLOADING;
            }
        }
    }

    @Override
    public void onDownLoad(ThreadTask task) {
        recorder.updateThreadTask(task);
        long finished = 0;
        List<ThreadTask> threadTasks = tTasksMap.get(task.url);
        for (int i = 0; i < threadTasks.size(); i++) {
            ThreadTask threadTask = threadTasks.get(i);
            if (threadTask.id == task.id) {
                threadTask.finished = task.finished;
            }
            finished += threadTask.finished;
        }
        Loker.d("----------------onDownLoad--------------------finished=" + finished);
        recorder.getTaskByUrl(task.url);
        Task task1 = recorder.getTaskByUrl(task.url);
        task1.completedSize = finished;
        recorder.updateTask(task1);
    }

    @Override
    public void onCompleted(ThreadTask task) {
        Loker.d("----------------onCompleted--------------------" + task.id);
        List<ThreadTask> threadTasks = tTasksMap.get(task.url);
        threadTasks.remove(task);
        if(threadTasks.size()==0){

        }
        recorder.getTaskByUrl(task.url);
    }
}
