package com.king.mobile.base;

import android.app.Application;
import android.util.Log;

public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(getPackageName(), "BaseApplication---------BaseApplication");
    }
}
