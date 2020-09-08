package com.king.mobile.downloadlib.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "download_thread_task")
public class ThreadTask {
    @Ignore
    private static final long serialVersionUID = 8822818790694831649L;
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(defaultValue = "0")
    public String url;
    @ColumnInfo(defaultValue = "0")
    public long size;
    @ColumnInfo(defaultValue = "0")
    public long start;
    @ColumnInfo(defaultValue = "0")
    public long finished;
    @ColumnInfo(defaultValue = "0")
    public boolean isFinished;
    @ColumnInfo
    public long createAt;

    @Override
    public String toString() {
        return "ThreadTask{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", size=" + size +
                ", start=" + start +
                ", finished=" + finished +
                ", isFinished=" + isFinished +
                ", createAt=" + createAt +
                '}';
    }
}
