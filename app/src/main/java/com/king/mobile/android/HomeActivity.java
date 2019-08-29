package com.king.mobile.android;

import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            Window window = getWindow();
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES;
            window.setAttributes(lp);
        }
        SplashFragment.show(getSupportFragmentManager());
    }


}
