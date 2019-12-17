package com.king.mobile.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

public class BaseApplication extends Application {

    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getBaseContext();
        Log.e(getPackageName(), "BaseApplication---------BaseApplication");
    }


}
