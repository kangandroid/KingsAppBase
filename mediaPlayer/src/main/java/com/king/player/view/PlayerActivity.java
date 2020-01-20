package com.king.player.view;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;

import com.king.mobile.util.Loker;
import com.king.mobile.util.ToastUtil;
import com.king.mobile.widget.TitleBar;
import com.king.player.R;
import com.king.player.model.VideoInfo;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.io.Serializable;

import static com.king.mobile.util.ImageUtil.loadCover;

public class PlayerActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer> {

    private StandardGSYVideoPlayer detailPlayer;
    private String url;
    private TitleBar titleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Intent intent = getIntent();
        Serializable extra = intent.getSerializableExtra("videoInfo");
        if (extra instanceof VideoInfo) {
            VideoInfo videoInfo = (VideoInfo) extra;
            String title = videoInfo.name;
            if (TextUtils.isEmpty(videoInfo.localPath)) {
                url = videoInfo.url;
            } else {
                url = videoInfo.localPath;
            }
            detailPlayer = findViewById(R.id.detail_player);
            Loker.d("title ====== " +title);
            titleBar = findViewById(R.id.title_bar);
            titleBar.setTitleTextColor(R.color.textWhite)
                    .setTitle(title)
                    .setLeftAction(TitleBar.ACTION_BACK_DARK)
                    .setTitleBarColorRes(R.color.transparent)
                    .invalidate();
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
        loadCover(imageView, url);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(url)
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return true;
    }
}
