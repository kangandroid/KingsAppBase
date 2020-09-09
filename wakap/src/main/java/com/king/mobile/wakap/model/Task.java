package com.king.mobile.wakap.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "TAB_ALARM_TASK")
public class Task {
    @Ignore
    private static final long serialVersionUID = 8822818790694831649L;
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo
    public String name;
    @ColumnInfo
    public long triggerAtMillis;
    @ColumnInfo
    public long intervalMillis;
    @ColumnInfo
    public String targetPackageName;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", triggerAtMillis=" + triggerAtMillis +
                ", intervalMillis=" + intervalMillis +
                ", targetPackageName='" + targetPackageName + '\'' +
                '}';
    }
}
