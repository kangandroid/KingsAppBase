package com.king.player.datasource;

import android.content.Context;
import android.text.TextUtils;

import com.king.mobile.util.ClipboardUtils;
import com.king.mobile.util.CommonRegexs;
import com.king.mobile.util.JSONUtil;
import com.king.player.model.VideoInfo;

import java.util.ArrayList;
import java.util.List;

public class ClipboardVideoSource implements IDataSource {

    private Context mContext;

    public ClipboardVideoSource(Context context) {
        this.mContext = context;
    }

    @Override
    public List<VideoInfo> getVideos() {
        String text = ClipboardUtils.getText(mContext);
        if (TextUtils.isEmpty(text)) return null;
        List<VideoInfo> videos;
        if (JSONUtil.isJson(text)) {
            if (JSONUtil.isJsonArray(text)) {
                videos = JSONUtil.parseArray(text, VideoInfo.class);
            } else {
                videos = new ArrayList<>();
                VideoInfo parse = JSONUtil.parse(text);
                if (TextUtils.isEmpty(parse.url)) return null;
                videos.add(parse);
            }
        } else if (text.matches(CommonRegexs.REGEX_URL)) {
            videos = new ArrayList<>();
            VideoInfo videoInfo = new VideoInfo();
            videoInfo.name = "未知";
            videoInfo.url = text;
            videos.add(videoInfo);
        } else {
            return null;
        }
        return videos;
    }
}
