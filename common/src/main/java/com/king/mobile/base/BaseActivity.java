package com.king.mobile.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.king.mobile.util.R;
import com.king.mobile.util.ScreenAdapter;
import com.king.mobile.util.ThemeManager;
import com.king.mobile.widget.TitleBar;
public abstract class BaseActivity extends AppCompatActivity {

    protected TitleBar titleBar;
    protected FrameLayout mContainer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScreenAdapter.resetDensity(this);
        if (isOverlay()) {
            setContentView(R.layout.activity_base_overlay);
        } else {
            setContentView(R.layout.activity_base);
        }
        titleBar = findViewById(R.id.title_bar);
        setTitle(titleBar);
        mContainer = findViewById(R.id.container);
        setContainer();
        inflateContentView();
        initView();
    }

    protected void setContainer(){
        ThemeManager.Theme currentTheme = ThemeManager.getInstance().getTheme();
        mContainer.setBackgroundColor(currentTheme.activityBackgrounColor);
    }


    protected abstract void initView();

    private void inflateContentView() {
        int contentLayoutId = getContentLayoutId();
        if (contentLayoutId == 0) {
            return;
        }
        LayoutInflater.from(this).inflate(contentLayoutId, mContainer);
    }

    protected abstract @LayoutRes int getContentLayoutId();


    protected void setTitle(TitleBar titleBar) {
    }

    protected boolean isOverlay() {
        return true;
    }


}
