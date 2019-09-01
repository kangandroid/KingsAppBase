package com.king.mobile.android;

import com.king.mobile.base.BaseActivity;
import com.king.mobile.widget.SplashFragment;
import com.king.mobile.widget.TitleBar;


public class HomeActivity extends BaseActivity {

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleBar.setTitle(getResources().getString(R.string.app_name))
                .setTitleTextColor("#FFFFFF")
                .setTitleBarColorRes(R.color.colorPrimary)
                .setLeftAction(new TitleBar.Action("ENV", 0, v ->{}))
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
    }


}
