package com.king.player.view;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.king.mobile.util.ImageUtil;
import com.king.mobile.util.ToastUtil;
import com.king.player.R;
import com.king.player.video.VideoInfo;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYVideoProgressListener;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.io.Serializable;
import java.util.HashMap;


public class PlayerActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {

    private StandardGSYVideoPlayer detailPlayer;
    private String url;
    private VideoInfo videoInfo;

    private GSYVideoProgressListener progressListener = (progress, secProgress, currentPosition, duration) -> {
        videoInfo.duration = duration; // 总时长
        videoInfo.latestPlayTime = System.currentTimeMillis() / 1000; // 当前播放时长
        videoInfo.position = currentPosition; // 当前播放时长
        videoInfo.progress = progress;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Intent intent = getIntent();
        Serializable extra = intent.getSerializableExtra("videoInfo");
        if (extra instanceof VideoInfo) {
            videoInfo = (VideoInfo) extra;
            if (TextUtils.isEmpty(videoInfo.localPath)) {
                url = videoInfo.url;
            } else {
                url = videoInfo.localPath;
            }
            detailPlayer = findViewById(R.id.detail_player);
            initVideoBuilderMode();
        } else {
            ToastUtil.show("视频信息出错");
        }

    }

    @Override
    public void onBackPressed() {
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        GSYVideoManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        GSYVideoManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoManager.releaseAllVideos();
    }

    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return detailPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        ImageUtil.loadCover(imageView, url);
        HashMap<String, String> mapHeadData = new HashMap<>(); // 视频请求头地址 例如：authority
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView) // 缩略图
                .setUrl(url) // 播放地址
                .setAutoFullWithSize(true) // 自动全屏
//                .setBottomProgressBarDrawable(getResources().getDrawable(R.drawable.progress_bar))
                .setCacheWithPlay(true) // 是否缓存
                .setVideoTitle(videoInfo.name) // 标题
                .setIsTouchWiget(true) //
                .setSeekRatio(0.3f)
                .setSeekOnStart(0) // 设置开始播放的位置
                .setRotateViewAuto(false)
                .setStartAfterPrepared(true) // 准备完成后自动播放
                .setMapHeadData(mapHeadData)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setVideoAllCallBack(this)
                .setFullHideActionBar(true)
                .setShrinkImageRes(R.drawable.ic_fullscreen_exit)
                .setEnlargeImageRes(R.drawable.ic_fullscreen)
                .setGSYVideoProgressListener(progressListener)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }


    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }

    @Override
    public void onPlayError(String url, Object... objects) {
        super.onPlayError(url, objects);
        // 播放出错时

    }
}
