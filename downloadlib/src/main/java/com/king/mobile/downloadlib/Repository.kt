package com.king.mobile.downloadlib

import android.content.Context
import java.util.HashMap

class Repository(context: Context) {

    var db: DownloadDB = DownloadDB.getDatabase(context)
    val taskDao = this.db.taskDao()
    val threadDao = this.db.threadTaskDao()

    fun getAllTask(): List<Task> {
        return taskDao.getAll()
    }

    fun create(task: Task) {
        taskDao.create(task)
    }

    fun getAllTreadTask(): List<ThreadTask> {
        return threadDao.getAllThreadTask()
    }

    fun getThreadTasksMap(): Map<String, List<ThreadTask>> {
        var tTasksMap: HashMap<String, List<ThreadTask>> = HashMap()
        val taskUrls = threadDao.getAllDownloadingTask()
        for (url in taskUrls) {
            val tTaskList = threadDao.findByUrl(url)
            tTasksMap[url] = tTaskList
        }
        return tTasksMap
    }


}