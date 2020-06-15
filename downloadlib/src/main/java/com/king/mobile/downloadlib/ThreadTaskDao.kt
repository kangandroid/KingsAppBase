package com.king.mobile.downloadlib

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ThreadTaskDao {
    @Query("SELECT * FROM TAB_THREAD_TASK ORDER BY createdAt DESC")
    fun getAllThreadTask(): List<ThreadTask> //查找所有

    @Query("SELECT distinct(url) FROM TAB_THREAD_TASK")
    fun getAllDownloadingTask(): List<String>  // 查处正在下载的任务

    @Query("SELECT * FROM TAB_THREAD_TASK WHERE id = :id LIMIT 1")
    fun findById(id: Long): ThreadTask  // 查找具体某一个线程任务

    @Query("SELECT * FROM TAB_THREAD_TASK WHERE url = :url")
    fun findByUrl(url: String): List<ThreadTask>  // 找出某一任务下的 线程任务

    @Update
    fun updateTask(task: ThreadTask) // 更新进度和状态

    @Insert
    fun creadte(vararg tasks: ThreadTask) // 创建任务

    @Delete
    fun delete(task: ThreadTask) // 删除任务 完成时

    @Query("delete FROM TAB_THREAD_TASK WHERE url = :url")
    fun deleteByUrl(url: String) // 删除一组任务 删除某个下载时用
}

