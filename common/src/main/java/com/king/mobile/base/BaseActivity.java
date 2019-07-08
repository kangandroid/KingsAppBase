package com.king.mobile.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.king.mobile.util.R;
import com.king.mobile.util.ThemeManager;
import com.king.mobile.widget.TitleBar;

public abstract class BaseActivity extends AppCompatActivity {

    protected TitleBar titleBar;
    protected FrameLayout mContent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarDarkFont(false).statusBarColorInt(Color.TRANSPARENT).init();
        if (isOverlay()) {
            setContentView(R.layout.activity_base_overlay);
        } else {
            setContentView(R.layout.activity_base);
        }
        titleBar = findViewById(R.id.title_bar);
        mContent = findViewById(R.id.container);
        inflateContentView();
        initView();
        ThemeManager.Theme currentTheme = ThemeManager.getInstance().getTheme();
        mContent.setBackgroundColor(currentTheme.activityBackgrounColor);
        updateTitle(currentTheme.titleBgColor);
    }

    protected abstract void initView();

    private void inflateContentView(){
        LayoutInflater.from(this).inflate(getContentLayoutId(),mContent);
    }

    protected abstract @LayoutRes int getContentLayoutId();


    private void updateTitle(int primarColor) {
        titleBar.setTitleBarColorInt(primarColor)
                .setTitle("BaseActivity")
                .setLeftAction(new TitleBar.Action(null, R.drawable.ic_chevron_left_black_24dp, v -> finish()))
                .setTitleTextColor(R.color.white)
                .invalidate();
    }

    protected boolean isOverlay() {
        return true;
    }



}
