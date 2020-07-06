package com.king.mobile.testapp;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        try {
            HookHelper.hookAMS();
            HookHelper.hookHandler();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
