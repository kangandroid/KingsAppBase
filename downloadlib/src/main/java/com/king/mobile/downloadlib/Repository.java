package com.king.mobile.downloadlib;

import android.content.Context;

import com.king.mobile.downloadlib.base.Recorder;
import com.king.mobile.downloadlib.db.DownloadDB;
import com.king.mobile.downloadlib.db.TaskDao;
import com.king.mobile.downloadlib.db.ThreadTaskDao;
import com.king.mobile.downloadlib.model.Task;
import com.king.mobile.downloadlib.model.ThreadTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

class Repository extends Recorder {
    private final TaskDao taskDao;
    private final ThreadTaskDao threadTaskDao;

    private static Repository instance;

    public static Repository getInstance(Context context) {
        if (instance == null) {
            instance = new Repository(context);
        }
        return instance;
    }

    private Repository(Context context) {
        DownloadDB db = DownloadDB.getDatabase(context);
        taskDao = db.taskDao();
        threadTaskDao = db.threadTaskDao();
    }


    public List<Task> getAllTask() {
        return taskDao.getAll();
    }

    public List<ThreadTask> getAllTreadTask() {
        return threadTaskDao.getAllThreadTask();
    }

    public boolean isThreadTaskCreated(String url) {
        List<ThreadTask> tTasks = threadTaskDao.findByUrl(url);
        return tTasks.size() > 0;
    }


    public List<ThreadTask> getThreadTasks(String url) {
        List<ThreadTask> tTasks = threadTaskDao.findByUrl(url);
        return tTasks;
    }

    Map<String, List<ThreadTask>> getThreadTasksMap() {
        HashMap<String, List<ThreadTask>> tTasksMap = new HashMap();
        List<String> taskUrls = threadTaskDao.getAllDownloadingTask();
        if (taskUrls.size() > 0) {
            for (String url : taskUrls) {
                List<ThreadTask> tTaskList = threadTaskDao.findByUrl(url);
                tTasksMap.put(url, tTaskList);
            }
        }
        return tTasksMap;
    }


    @Override
    public void addTask(Task task) {
        taskDao.create(task);
    }

    @Override
    public void updateTask(Task task) {
        taskDao.updateTask(task);
    }

    @Override
    public Task getTaskByUrl(String url) {
        return taskDao.findOneByUrl(url);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskDao.findOneById(id);
    }

    @Override
    public boolean idTaskExist(String url) {
        return taskDao.findOneByUrl(url) != null;
    }

    @Override
    public void addThreadTask(ThreadTask threadTask) {
        threadTaskDao.create(threadTask);
    }

    public void updateThreadTask(ThreadTask threadTask) {
        threadTaskDao.updateTask(threadTask);
    }

    public List<Task> getWaitingTask() {
        return taskDao.getTasksByState(TaskState.STATE_WAITING);
    }
}