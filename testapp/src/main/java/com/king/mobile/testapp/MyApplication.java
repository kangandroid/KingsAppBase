package com.king.mobile.testapp;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.king.mobile.testapp.utils.LogUtil;
import com.king.mobile.testapp.utils.StartMonitor;
import com.king.mobile.testapp.utils.TestUncaughtExceptionHandler;

public class MyApplication extends Application {
    @Override
    protected void attachBaseContext(Context base) {
        StartMonitor.getInstance().startBegin();
        super.attachBaseContext(base);
        LogUtil.print("MyApplication","attachBaseContext");
        Thread.currentThread().setUncaughtExceptionHandler(new TestUncaughtExceptionHandler());
        try {
//            HookHelper.hookAMS();
//            HookHelper.hookHandler();
//            PhantomCore.getInstance().init(this, new PhantomCore.Config());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.print("MyApplication","onCreate");
        if (BuildConfig.DEBUG) {
            ARouter.openLog();
            ARouter.openDebug();
        }
        ARouter.init(this);
    }
}
