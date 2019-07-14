package com.king.mobile.android;

import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.king.mobile.base.BaseActivity;
import com.king.mobile.widget.TitleBar;

public class HomeActivity extends BaseActivity {
    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void setTitle(TitleBar titleBar) {
        titleBar.setVisibility(View.GONE);
    }

    @Override
    protected boolean isOverlay() {
        return true;
    }

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        mTextMessage = findViewById(R.id.message);
//        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
