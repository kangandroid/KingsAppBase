package com.king.mobile.wakap.model;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Query("SELECT * FROM TAB_ALARM_TASK")
    LiveData<List<Task>> getAll();

    @Insert
    void addTask(Task task);
}
