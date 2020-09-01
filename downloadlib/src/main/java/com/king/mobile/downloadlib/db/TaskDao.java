package com.king.mobile.downloadlib.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.king.mobile.downloadlib.model.Task;

import java.util.List;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM download_task ORDER BY createdAt DESC")
    List<Task> getAll();  // 获取全部任务

    @Query("SELECT * FROM download_task WHERE url IN (:urls)")
    List<Task> loadAllByIds(List<String> urls); // 获取多个任务

    @Query("SELECT * FROM download_task WHERE state = :state")
    List<Task> getTasksByState(int state); // 按状态查找任务

    @Query("SELECT * FROM download_task WHERE id = :id LIMIT 1")
    Task findOneById(Long id); // 通过 id 获取某一个任务

    @Query("SELECT * FROM download_task WHERE url = :url LIMIT 1")
    Task findOneByUrl(String url); // 通过url 获取某一个任务

    @Update
    void updateTask(Task task); // 更新任务信息 或状态

    @Insert
    void create(Task tasks);// 创建一个或多个任务

    @Delete
    void delete(Task tasks);// 删除一个或多个
}
