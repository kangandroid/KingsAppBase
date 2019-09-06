package com.king.mobile.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.king.mobile.util.R;
import com.king.mobile.util.ThemeManager;
import com.king.mobile.widget.TitleBar;

public abstract class BaseActivity extends AppCompatActivity {

    protected TitleBar titleBar;
    protected FrameLayout mContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        View decorView = window.getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE // 16
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION //16
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // 16
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // 隐藏导航栏（底部）
//                | View.SYSTEM_UI_FLAG_FULLSCREEN // 16 全屏隐藏状态栏
                        | View.SYSTEM_UI_FLAG_IMMERSIVE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        ); // 19


        if (isOverlay()) {
            setContentView(R.layout.activity_base_overlay);
        } else {
            setContentView(R.layout.activity_base);
        }
        titleBar = findViewById(R.id.title_bar);
        mContainer = findViewById(R.id.container);
        ThemeManager.Theme currentTheme = ThemeManager.getInstance().getTheme();
        mContainer.setBackgroundColor(currentTheme.activityBackgrounColor);
        setTitle(titleBar);
        inflateContentView();
        initView();
    }

    protected abstract void initView();

    private void inflateContentView() {
        int contentLayoutId = getContentLayoutId();
        if (contentLayoutId == 0) {
            return;
        }
        LayoutInflater.from(this).inflate(contentLayoutId, mContainer);
    }

    protected abstract @LayoutRes
    int getContentLayoutId();


    protected void setTitle(TitleBar titleBar) {
        ThemeManager.Theme currentTheme = ThemeManager.getInstance().getTheme();
        titleBar.setTitleBarColorInt(currentTheme.titleBgColor)
                .setTitle(getTitle().toString())
                .setLeftAction(new TitleBar.Action(null, R.drawable.ic_chevron_left_black_24dp, v -> finish()))
                .setTitleTextColorInt(currentTheme.titleFontColor)
                .invalidate();
    }

    protected boolean isOverlay() {
        return true;
    }


}
