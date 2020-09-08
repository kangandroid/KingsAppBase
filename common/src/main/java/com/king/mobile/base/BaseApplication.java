package com.king.mobile.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.king.mobile.util.ScreenAdapter;

public class BaseApplication extends Application {

    private static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getBaseContext();
        ScreenAdapter.init(750);
        this.registerActivityLifecycleCallbacks(AppWatcher.getInstance());
        Log.e(getPackageName(), "BaseApplication---------BaseApplication");
    }


}
