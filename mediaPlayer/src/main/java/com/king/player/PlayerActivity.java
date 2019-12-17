package com.king.player;

import android.widget.AbsListView;

import com.king.mobile.base.BaseActivity;
import com.shuyu.gsyvideoplayer.GSYVideoManager;

public class PlayerActivity extends BaseActivity {


    @Override
    protected void initView() {
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_player;
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
}
