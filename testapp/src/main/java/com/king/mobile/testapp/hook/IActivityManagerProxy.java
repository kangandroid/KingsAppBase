package com.king.mobile.testapp.hook;

import android.content.Intent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class IActivityManagerProxy implements InvocationHandler {
    private Object mActivityManager;
    private String targetComponentName;
    private String packageName;
    public static final String TAG = "IActivityManagerProxy";
    String hookMethodName;
    private static final String START_ACTIVITY = "startActivity";
    private static final String START_SERVICE = "startService";

    public IActivityManagerProxy(Object mActivityManager, String hookMethodName,String targetComponentName, String packageName) {
        this.mActivityManager = mActivityManager;
        this.targetComponentName = targetComponentName;
        this.hookMethodName = hookMethodName;
        this.packageName = packageName;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        replaceIntent(START_ACTIVITY,method, args);
        return method.invoke(mActivityManager,args);
    }

    private void replaceIntent(String hookMethodName, Method method, Object[] args) {
        if (hookMethodName.equals(method.getName())) {
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
            subIntent.setClassName(packageName,packageName+targetComponentName);
            subIntent.putExtra(HookHelper.TARGET_INTENT,intent);
            args[index] = subIntent;
        }
    }
}
