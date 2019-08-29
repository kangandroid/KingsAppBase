package com.king.mobile.base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.king.mobile.util.R;
import com.king.mobile.util.ThemeManager;
import com.king.mobile.widget.TitleBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public abstract class BaseActivity extends AppCompatActivity {

    protected TitleBar titleBar;
    protected SmartRefreshLayout mContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ImmersionBar.with(this).statusBarColorInt(Color.TRANSPARENT).init();
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
