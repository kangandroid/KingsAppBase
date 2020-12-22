package com.king.player.datasource;

import com.king.player.video.VideoInfo;

import java.util.List;

interface IDataSource {
    List<VideoInfo> getVideos();
}
