package com.king.mobile.testapp.daemon;

import android.app.Activity;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

import java.lang.ref.SoftReference;


public class ScreenManager {
    private static ScreenManager instance;
    private SoftReference<KeepAliveActivity> srA;

    public static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }


    public void finishAliveActivity() { //结束掉 LiveActivity
        if (srA != null) {
            Activity activity = srA.get();
            if (activity != null) {
                activity.finish();
            }
        }
    }

    public void setActivity(KeepAliveActivity aliveActivity) {
        srA = new SoftReference<>(aliveActivity);
    }

    private ScreenManager() {
    }
}
