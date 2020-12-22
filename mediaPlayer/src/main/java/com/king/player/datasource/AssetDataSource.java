package com.king.player.datasource;

import android.content.Context;

import com.king.mobile.util.AssetsUtil;
import com.king.mobile.util.JSONUtil;
import com.king.player.video.VideoInfo;

import java.io.IOException;
import java.util.List;

public class AssetDataSource implements IDataSource {
    private Context mContext;

    public AssetDataSource(Context context) {
        this.mContext = context;
    }

    @Override
    public List<VideoInfo> getVideos() {
        try {
            String tvJson = AssetsUtil.readText(mContext, "LiveTVm3u8.json");
            List<VideoInfo> videoInfos = JSONUtil.parseArray(tvJson, VideoInfo.class);
            if (videoInfos.size() > 0) {
                for (VideoInfo info : videoInfos) {
                    info.type = 2; // 电视直播
                    info.createTime = System.currentTimeMillis()/1000;
                }
            }
            return videoInfos;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
