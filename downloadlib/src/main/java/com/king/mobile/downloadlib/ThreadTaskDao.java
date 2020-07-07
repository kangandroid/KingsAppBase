package com.king.mobile.downloadlib;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

//@Dao
interface ThreadTaskDao {
    //    @Query("SELECT * FROM TAB_THREAD_TASK ORDER BY createdAt DESC")
    List<ThreadTask> getAllThreadTask(); //查找所有

    //    @Query("SELECT distinct(url) FROM TAB_THREAD_TASK")
    List<String> getAllDownloadingTask();  // 查处正在下载的任务

    //    @Query("SELECT * FROM TAB_THREAD_TASK WHERE id = :id LIMIT 1")
    ThreadTask findById(long id);  // 查找具体某一个线程任务

    //    @Query("SELECT * FROM TAB_THREAD_TASK WHERE url = :url")
    List<ThreadTask> findByUrl(String url);   // 找出某一任务下的 线程任务

    //    @Update
    void updateTask(ThreadTask task); // 更新进度和状态

    //    @Insert
    void creadte(ThreadTask tasks);// 创建任务

    //    @Delete
    void delete(ThreadTask task); // 删除任务 完成时

    //    @Query("delete FROM TAB_THREAD_TASK WHERE url = :url")
    void deleteByUrl(String url);// 删除一组任务 删除某个下载时用
}

