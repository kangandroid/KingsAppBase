package com.king.mobile.wakap;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import com.king.mobile.base.BaseActivity;
import com.king.mobile.widget.TitleBar;

public class InstallAppActivity extends BaseActivity {

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleBar.setTitleBarColorRes(R.color.colorPrimaryDark)
                .setTitle(R.string.select_app)
                .setTitleTextColor(R.color.colorAccent)
                .invalidate();
    }

    @Override
    protected void initView() {
        ApplicationFragment applicationFragment = new ApplicationFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.container, applicationFragment).commit();
    }

    @Override
    protected int getContentLayoutId() {
        return 0;
    }

    @Override
    protected boolean isOverlay() {
        return false;
    }
}