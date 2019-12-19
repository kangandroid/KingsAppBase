package com.king.player.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface VideoDao {
    @Query("SELECT * FROM tab_videos")
    List<VideoInfo> getAll();

    @Query("SELECT * FROM tab_videos order by create_time desc limit :start,:limit")
    List<VideoInfo> getLimit(int start, int limit);

    @Query("select * from tab_videos where url= :url limit 1")
    VideoInfo findByUrl(String url);

    @Insert
    Long insert(VideoInfo videoInfo);

    @Insert
    List<Long> insertAll(List<VideoInfo> videoInfo);

    @Update
    Integer update(VideoInfo... videoInfo);

    @Delete
    Integer delete(VideoInfo videoInfo);
}