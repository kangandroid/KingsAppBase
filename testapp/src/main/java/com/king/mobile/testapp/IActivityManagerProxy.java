package com.king.mobile.testapp;

import android.content.Intent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IActivityManagerProxy implements InvocationHandler {
    private Object mActivityManager;
    private String targetActivityName;
    private String packageName;
    public static final String TAG = "IActivityManagerProxy";


    public IActivityManagerProxy(Object mActivityManager, String targetActivityName, String packageName) {
        this.mActivityManager = mActivityManager;
        this.targetActivityName = targetActivityName;
        this.packageName = packageName;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("startActivity".equals(method.getName())) {
            Intent intent = null;
            int index = 0;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent) {
                    index = i;
                    break;
                }
            }
            intent = (Intent)args[index];
            Intent subIntent= new Intent();
            subIntent.setClassName(packageName,packageName+targetActivityName);
            subIntent.putExtra(HookHelper.TARGET_INTENT,intent);
            args[index] = subIntent;
        }
        return method.invoke(mActivityManager,args);
    }
}
