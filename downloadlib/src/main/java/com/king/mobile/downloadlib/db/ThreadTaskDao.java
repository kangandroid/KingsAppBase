package com.king.mobile.downloadlib.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.king.mobile.downloadlib.model.ThreadTask;

import java.util.List;

@Dao
public interface ThreadTaskDao {
    @Query("SELECT * FROM download_thread_task ORDER BY createAt DESC")
    List<ThreadTask> getAllThreadTask(); //查找所有

    @Query("SELECT distinct(url) FROM download_thread_task")
    List<String> getAllDownloadingTask();  // 查处正在下载的任务

    @Query("SELECT * FROM download_thread_task WHERE id = :id LIMIT 1")
    ThreadTask findById(long id);  // 查找具体某一个线程任务

    @Query("SELECT * FROM download_thread_task WHERE url = :url")
    List<ThreadTask> findByUrl(String url);   // 找出某一任务下的 线程任务

    @Update
    void updateTask(ThreadTask task); // 更新进度和状态

    @Insert
    void create(ThreadTask tasks);// 创建任务

    @Delete
    void delete(ThreadTask task); // 删除任务 完成时

    @Query("delete FROM download_thread_task WHERE url = :url")
    void deleteByUrl(String url);// 删除一组任务 删除某个下载时用
}

