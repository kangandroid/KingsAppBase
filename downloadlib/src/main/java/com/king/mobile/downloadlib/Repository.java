package com.king.mobile.downloadlib;

import android.content.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Repository {
    Context mContext;
    private final TaskDao taskDao;
    private final ThreadTaskDao threadTaskDao;

    Repository(Context context) {
        mContext = context;
        DownloadDB db = DownloadDB.getDatabase(mContext);
        taskDao = db.taskDao();
        threadTaskDao = db.threadTaskDao();
    }


    List<Task> getAllTask() {
        return taskDao.getAll();
    }

    void create(Task task) {
        taskDao.create(task);
    }

    List<ThreadTask> getAllTreadTask() {
        return threadTaskDao.getAllThreadTask();
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


}