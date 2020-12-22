package com.king.player.search;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;


@Dao
public interface WebSiteDao {
    // 增
    @Insert
    Maybe<Long> insert(WebSiteInfo info);

    @Insert
    Completable insertAll(List<WebSiteInfo> videoInfo);
    // 删

    @Delete
    Completable delete(WebSiteInfo info);

    @Delete
    Completable deleteAll(List<WebSiteInfo> videoInfo);
    // 改

    @Update
    Single<Integer> update(WebSiteInfo info);

    @Update
    Completable updateAll(List<WebSiteInfo> videoInfo);

    // 查询全部
    @Query("SELECT * FROM WEB_SITE")
    LiveData<List<WebSiteInfo>> getAll();

    /**
     * 分页查询
     *
     * @param start
     * @param limit
     * @return
     */
    @Query("SELECT * FROM WEB_SITE order by useTimes desc limit :start,:limit")
    LiveData<List<WebSiteInfo>> getByPage(int start, int limit);

    /**
     * 通过URL 获取
     *
     * @param url
     * @return
     */
    @Query("select * from WEB_SITE where url= :url limit 1")
    WebSiteInfo findOneByUrl(String url);

    /**
     * 获取默认
     *
     * @return
     */
    @Query("select * from WEB_SITE where isDefault= '1' limit 1")
    LiveData<WebSiteInfo> getDefault();


    @Query("update WEB_SITE set isDefault = 0 where isDefault = 1; ")
    Maybe<Integer> cancelDefault();

}
