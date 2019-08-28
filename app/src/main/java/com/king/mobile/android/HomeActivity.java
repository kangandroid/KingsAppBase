package com.king.mobile.android;

import com.king.mobile.base.BaseActivity;
import com.king.mobile.widget.SplashFragment;
import com.king.mobile.widget.TitleBar;


public class HomeActivity extends BaseActivity {


    private SplashFragment splashFragment;

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleBar.setTitle("德美")
                .setTitleTextColor("#FFFFFF")
                .setTitleBarColorRes(R.color.colorAccent)
                .setLeftAction(new TitleBar.Action("闪屏",0,v -> SplashFragment.show(getSupportFragmentManager())))
                .invalidate();
    }

    @Override
    protected boolean isOverlay() {
        return false;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        SplashFragment.show(getSupportFragmentManager());
    }


}
