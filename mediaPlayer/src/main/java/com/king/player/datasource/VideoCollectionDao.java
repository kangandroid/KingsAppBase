package com.king.player.datasource;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.king.player.model.VideoCollection;
import com.king.player.model.VideoInfo;

import java.util.List;


@Dao
public interface VideoCollectionDao {
    /**
     * 查询全部
     *
     * @return
     */
    @Query("SELECT * FROM tab_collection order by date_created desc")
    LiveData<List<VideoCollection>> getAll();


    /**
     * 通过URL 获取视屏信息
     *
     * @param id
     * @return
     */
    @Query("select * from tab_collection where id= :id limit 1")
    VideoCollection findById(long id);


    @Insert
    void insert(VideoCollection videoCollection);

    @Insert
    void insertAll(List<VideoCollection> videoCollections);

    @Update
    Integer update(VideoCollection videoCollection);

    @Delete
    void delete(VideoCollection videoCollection);

}
