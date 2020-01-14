package com.king.player;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.text.TextUtils;

import com.king.mobile.util.Loker;
import com.king.mobile.util.ToastUtil;

import java.util.Stack;

public class AppWatcher implements Application.ActivityLifecycleCallbacks {

    Stack<Activity> stack = new Stack<>();
    int startedActivityCount = 0;
    boolean isOnBack = true;

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        Loker.d("--------------onActivityStarted");
        startedActivityCount++;
        if(isOnBack){
            ToastUtil.show("应用已切换到前台");
            Loker.d("--------------应用已切换到前台");
        }
        isOnBack = false;
    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {
        startedActivityCount--;
        if (startedActivityCount == 0) {
            isOnBack = true;
            ToastUtil.show("应用已切换到后台");
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }
}
