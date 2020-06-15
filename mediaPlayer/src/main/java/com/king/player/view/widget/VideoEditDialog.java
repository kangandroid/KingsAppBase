package com.king.player.view.widget;

import android.view.View;

import com.king.mobile.widget.BaseDialog;
import com.king.player.R;
import com.king.player.model.VideoInfo;

class VideoEditDialog extends BaseDialog {
    public VideoEditDialog(VideoInfo videoInfo) {
    }

    @Override
    protected int setLayoutId() {
        return R.layout.dialog_edit;
    }

    @Override
    protected void initView(View mRootView) {

    }
}
