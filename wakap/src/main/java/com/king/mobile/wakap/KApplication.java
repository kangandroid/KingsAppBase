package com.king.mobile.wakap;

import android.view.View;

import com.king.mobile.base.BaseApplication;
import com.king.mobile.util.ScreenAdapter;

import dagger.hilt.android.HiltAndroidApp;

//@HiltAndroidApp
public class KApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ScreenAdapter.init(750f);
    }

}

