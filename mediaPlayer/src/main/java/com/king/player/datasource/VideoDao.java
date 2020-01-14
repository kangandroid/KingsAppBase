package com.king.player.datasource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.king.player.model.VideoInfo;

import java.util.List;


@Dao
public interface VideoDao {
    /**
     * 查询全部
     *
     * @return
     */
    @Query("SELECT * FROM tab_videos")
    List<VideoInfo> getAll();

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @return
     */
    @Query("SELECT * FROM tab_videos order by create_time desc limit :start,:limit")
    List<VideoInfo> getByPage(int start, int limit);

    /**
     * 通过URL 获取视屏信息
     *
     * @param url
     * @return
     */
    @Query("select * from tab_videos where url= :url limit 1")
    VideoInfo findOneByUrl(String url);

    /**
     * 通过URL 获取视屏信息
     *
     * @param localId
     * @return
     */
    @Query("select * from tab_videos where local_id= :localId limit 1")
    VideoInfo findOneByLocalId(String localId);

    /**
     * 查询全部本地视频
     *
     * @return
     */
    @Query("select * from tab_videos where local_id is not null ORDER BY create_time DESC")
    LiveData<List<VideoInfo>> findLocal();/**
     * 查询全部本地视频
     *
     * @return
     */
    @Query("select * from tab_videos where local_id is not null ORDER BY create_time DESC")
    List<VideoInfo> findLocalList();

    @Insert
    void insert(VideoInfo videoInfo);

    @Insert
    void insertAll(List<VideoInfo> videoInfo);

    @Update
    Integer update(VideoInfo... videoInfo);

    @Delete
    public void deleteVideo(VideoInfo video);


    @Query("delete from tab_videos where local_id != null")
    int deleteLocal();

    /**
     * @return
     */
    @Query("select * from tab_videos where local_id is null")
    LiveData<List<VideoInfo>> findRemote();

}
