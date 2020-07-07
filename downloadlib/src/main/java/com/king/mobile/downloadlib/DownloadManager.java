package com.king.mobile.downloadlib;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.king.mobile.downloadlib.DownloadThread.Listener;
import com.king.mobile.util.Executor;
import com.king.mobile.util.InstanceUtil;
import com.king.mobile.util.Loker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Response;
import okhttp3.ResponseBody;

public class DownloadManager implements Listener {
    Context mContext;
    private ExecutorService downloadExecutor;
    private static DownloadManager mInstance;

    private DownloadManager(Context context) {
        List<Task> tasks = repository.getAllTask();
        List<ThreadTask> tTasks = repository.getAllTreadTask();
        tTasksMap = repository.getThreadTasksMap();
    }


    private Repository repository = new Repository(mContext);
    private Map<String, List<ThreadTask>> tTasksMap; // 正在下载的任务
    private Queue<Task> waitingList = new ConcurrentLinkedDeque();// 等待的任务队列


    private long THREAD_TASK_MAX_SIZE = 10 * 1024 * 1024L;
    int taskCount = 3;
    String DOWNLOAD_DIR = "${context.filesDir.absolutePath}/download";

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


    private Task createTask(String url) {
        if (isTaskExist(url)) {
            return null;
        }
        int lastIndexOf = url.lastIndexOf('.');
        String type = url.substring(lastIndexOf + 1);
        String fileName = "${UUID.randomUUID()}.${type}";
        String path = "$DOWNLOAD_DIR/$fileName";
        Task task = new Task();
        task.url = url;
        task.name = fileName;
        task.path = path;
        task.size = 0;
        task.state = TaskState.STATE_CREATE.code;
        task.progress = 0f;
        task.completedSize = 0;
        task.createdAt = System.currentTimeMillis();
        task.type = type;
        task.priority = 0;
        task.md5 = "";
        repository.create(task);
        return task;
    }

    private boolean isTaskExist(String url) {
        List<ThreadTask> task = tTasksMap.get(url);
        return (task != null);
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
    void start(final Task task) {
        //首先判断 任务是否已存在于下载列表
        if (tTasksMap.containsKey(task.url)) { // 如果存在则

        } else if (tTasksMap.size() < taskCount) { // 正在下载数还未满
            Executor.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Response response = OkHttpHelper.doRequest(task.url);
                        ResponseBody body = response.body();
                        long size = 0;
                        if (response.code() == 200) {
                            size = body.contentLength();
                            File dir = new File(DOWNLOAD_DIR);
                            if (!dir.exists()) {
                                dir.mkdir();
                            }
                            createThreadTasks(task);
                        } else {
                            task.state = -1;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        } else { // 添加到等待队列
            waitingList.add(task);
        }

    }

    private void createThreadTasks(Task task) {
        if (tTasksMap.containsKey(task.url)) { // 如果存在则
            List<ThreadTask> tTasks = tTasksMap.get(task.url);
        } else {
            double taskCount = Math.ceil((task.size / THREAD_TASK_MAX_SIZE));
            List list = new ArrayList<ThreadTask>();
            for (int i = 0; i < taskCount; i++) {
                long size;
                if (i == taskCount - 1) {
                    size = task.size - THREAD_TASK_MAX_SIZE * i;
                } else {
                    size = THREAD_TASK_MAX_SIZE;
                }
                ThreadTask threadTask = new ThreadTask();
                threadTask.url = task.url;
                threadTask.size = size;
                threadTask.start = THREAD_TASK_MAX_SIZE * i;
                threadTask.finished = 0;
                threadTask.createAt = System.currentTimeMillis() / 1000;
                threadTask.id = 0L;
                threadTask.isFinished = false;
                list.add(threadTask);
                Executor.getInstance().execute(new DownloadThread(threadTask, task));
            }
        }

    }

    /**
     * 立即开始某个任务
     */
    public void startNow(Task task) {

    }

    /**
     * 开始下载列表
     */
    public void start() {

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
        Task task = createTask(url);
        if (task != null) start(task);
        else {
            Loker.d("---------------task == null");
        }
    }

    @Override
    public void onDownLoad(ThreadTask task) {

    }

    @Override
    public void onCompleted(ThreadTask task) {

    }
}
