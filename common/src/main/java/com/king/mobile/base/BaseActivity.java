package com.king.mobile.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.barlibrary.ImmersionBar;
import com.king.mobile.util.R;
import com.king.mobile.util.ThemeManager;
import com.king.mobile.widget.TitleBar;

public class BaseActivity extends AppCompatActivity {

    protected TitleBar titleBar;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(isOverlay()){
            setContentView(R.layout.activity_base_overlay);
        }else {
            setContentView(R.layout.activity_base);
        }
        titleBar = findViewById(R.id.title_bar);
        titleBar.setTitleBarColor(ThemeManager.getPrimarColor())
                .setTitle("BaseActivity")
                .setTitleTextColor(R.color.white);
        ImmersionBar.with(this).statusBarColor(R.color.transparent)
                .statusBarDarkFont(false).init();
    }

    protected boolean isOverlay() {
        return false;
    }

    protected boolean isStateBarTran() {
        return false;
    }


}
