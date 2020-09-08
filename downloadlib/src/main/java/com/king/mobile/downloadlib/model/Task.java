package com.king.mobile.downloadlib.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "download_task")
public class Task {
    @Ignore
    private static final long serialVersionUID = 8822818790694831649L;
    @PrimaryKey(autoGenerate = true)
    public long id;
    @ColumnInfo(name = "url")  // 名称
    public String url;
    @ColumnInfo
    public String name;
    @ColumnInfo
    public String path;
    @ColumnInfo
    public long size;
    @ColumnInfo(defaultValue = "")
    public String md5;
    /**
     *     STATE_URL_ERROR("下载地址有误", -1),  // 地址有误
     *     STATE_CREATE("初始状态", 0), // 初始状态
     *     STATE_START("开始下载", 1),  // 任务分配完成，地址无误可以下载
     *     STATE_DOWNLOADING("下载中", 2), // 下载中
     *     STATE_STOP("暂停下载", 3),// 暂停中
     *     STATE_DOWNLOAD_ERROR("下载出错", 4),  // 下载出错
     *     STATE_FINISHED("下载完成", 100);// 已完成
     */
    @ColumnInfo
    public int state;
    @ColumnInfo
    public long createdAt;
    @ColumnInfo
    public String type;
    @ColumnInfo
    public float progress;
    @ColumnInfo
    public int priority;
    @ColumnInfo(defaultValue = "0.0")
    public long completedSize;

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", size=" + size +
                ", md5='" + md5 + '\'' +
                ", state=" + state +
                ", createdAt=" + createdAt +
                ", type='" + type + '\'' +
                ", progress=" + progress +
                ", priority=" + priority +
                ", completedSize=" + completedSize +
                '}';
    }
}
