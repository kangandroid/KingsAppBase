package com.king.mobile.android;

import android.view.View;

import com.king.mobile.base.BaseActivity;
import com.king.mobile.component.Component;
import com.king.mobile.component.ComponentManager;
import com.king.mobile.widget.SplashFragment;
import com.king.mobile.widget.TitleBar;


public class HomeActivity extends BaseActivity {

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleBar.setTitle(getResources().getString(R.string.app_name))
                .setTitleTextColor("#FFFFFF")
                .setTitleBarColorRes(R.color.white)
                .setLeftAction(new TitleBar.Action("ENV", 0, v -> {
                }))
                .invalidate();
    }

    @Override
    protected boolean isOverlay() {
        SplashFragment.show(getSupportFragmentManager());
        return false;
    }


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        View view = ComponentManager.getViewComponet("Camera", "CameraView", this);
    }


}
