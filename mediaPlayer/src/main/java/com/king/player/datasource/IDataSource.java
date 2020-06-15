package com.king.player.datasource;

import com.king.player.model.VideoInfo;

import java.util.List;

interface IDataSource {
    List<VideoInfo> getVideos();
}
