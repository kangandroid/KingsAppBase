package com.king.mobile.base;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.util.DebugUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

import com.king.mobile.util.Loker;
import com.king.mobile.util.ScreenAdapter;
import com.king.refresh.util.DisplayUtil;

import java.lang.reflect.Method;

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
        registerActivityLifecycleCallbacks(AppWatcher.getInstance());
    }
//
//
//    @Override
//    protected void attachBaseContext(Context base) {
//        Resources res = base.getResources();
//        final Configuration configuration = res.getConfiguration();
//        configuration.setToDefaults();
//        configuration.densityDpi = 1;
//        DisplayMetrics displayMetrics = res.getDisplayMetrics();
//        displayMetrics.scaledDensity = 1;
//        res.updateConfiguration(configuration, displayMetrics);
//        final Context newContext = base.createConfigurationContext(configuration);
//        super.attachBaseContext(newContext);
//    }




}
