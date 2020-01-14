package com.king.mobile.downloadlib

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM TAB_TASK ORDER BY createdAt DESC")
    fun getAll(): List<Task>

    @Query("SELECT * FROM TAB_TASK WHERE id IN (:userIds)")
    fun loadAllByIds(userIds: LongArray): List<Task>

    @Query("SELECT * FROM TAB_TASK WHERE id = :id LIMIT 1")
    fun findOneById(id: Long): Task

    @Query("SELECT * FROM TAB_TASK WHERE url = :url LIMIT 1")
    fun findOneByUrl(url: String): Task

    @Update
    fun updateTask(task: Task)

    @Insert
    fun insertAll(vararg tasks: Task)

    @Delete
    fun delete(task: Task)
}

