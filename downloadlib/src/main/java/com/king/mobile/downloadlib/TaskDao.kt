package com.king.mobile.downloadlib

import androidx.room.*

@Dao
interface TaskDao {

    @Query("SELECT * FROM TAB_TASK ORDER BY createdAt DESC")
    fun getAll(): List<Task> // 获取全部任务

    @Query("SELECT * FROM TAB_TASK WHERE url IN (:urls)")
    fun loadAllByIds(urls: List<String>): List<Task> // 获取多个任务

    @Query("SELECT * FROM TAB_TASK WHERE id = :id LIMIT 1")
    fun findOneById(id: Long): Task // 通过 id 获取某一个任务

    @Query("SELECT * FROM TAB_TASK WHERE url = :url LIMIT 1")
    fun findOneByUrl(url: String): Task // 通过url 获取某一个任务

    @Update
    fun updateTask(task: Task) // 更新任务信息 或状态

    @Insert
    fun create(vararg tasks: Task) // 创建一个或多个任务

    @Delete
    fun delete(vararg tasks: Task) // 删除一个或多个
}

