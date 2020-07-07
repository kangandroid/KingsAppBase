package com.king.mobile.downloadlib;

import java.util.List;

interface TaskDao {
    //    @Query("SELECT * FROM TAB_TASK ORDER BY createdAt DESC")
    List<Task> getAll();  // 获取全部任务

    //    @Query("SELECT * FROM TAB_TASK WHERE url IN (:urls)")
    List<Task> loadAllByIds(List<String> urls); // 获取多个任务

    //    @Query("SELECT * FROM TAB_TASK WHERE id = :id LIMIT 1")
    Task findOneById(Long id); // 通过 id 获取某一个任务

    //    @Query("SELECT * FROM TAB_TASK WHERE url = :url LIMIT 1")
    Task findOneByUrl(String url); // 通过url 获取某一个任务

    //    @Update
    void updateTask(Task task); // 更新任务信息 或状态

    //    @Insert
    void create(Task tasks);// 创建一个或多个任务

    //    @Delete
    void delete(Task tasks);// 删除一个或多个
}
