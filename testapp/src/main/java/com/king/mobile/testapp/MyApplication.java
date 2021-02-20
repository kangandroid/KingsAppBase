package com.king.mobile.testapp;

import android.app.Application;
import android.content.Context;
import android.os.Debug;
import android.os.StrictMode;

import com.alibaba.android.arouter.launcher.ARouter;
import com.king.mobile.testapp.utils.LogUtil;
import com.king.mobile.testapp.utils.StartMonitor;
import com.king.mobile.testapp.utils.TestUncaughtExceptionHandler;

import java.io.File;

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
            startStrictMode();

        }
        File filesDir = getExternalFilesDir(null);
        String absolutePath = filesDir.getAbsolutePath();
        LogUtil.print("absolutePath",absolutePath);
        Debug.startMethodTracing(absolutePath);
        ARouter.init(this);
        Debug.stopMethodTracing();
//        Debug.startNativeTracing();
//        Debug.stopNativeTracing();

        System.loadLibrary("");
    }

    private void startStrictMode() {
        StrictMode.ThreadPolicy threadPolicy = new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .detectCustomSlowCalls()
                .penaltyDialog()
                .penaltyLog()
                .build();
        StrictMode.setThreadPolicy(threadPolicy);
        StrictMode.VmPolicy vmPolicy = new StrictMode.VmPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .build();
        StrictMode.setVmPolicy(vmPolicy);
//        StrictMode.enableDefaults();
    }
}
