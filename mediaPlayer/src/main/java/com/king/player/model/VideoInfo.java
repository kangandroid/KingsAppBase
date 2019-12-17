package com.king.player.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tab_videos")
public class VideoInfo {
    @PrimaryKey(autoGenerate = true)
    public int id ;
    @ColumnInfo(name = "name")
    public String name;
    @ColumnInfo(name = "create_time")
    public long createTime;
    @ColumnInfo(name = "url")
    public String url;
}
