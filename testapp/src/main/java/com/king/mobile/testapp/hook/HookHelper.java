package com.king.mobile.testapp.hook;

import android.os.Build;
import android.os.Handler;

import com.king.mobile.testapp.FieldUtil;
import com.king.mobile.testapp.HCallBack;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

public class HookHelper {
    public static final String TARGET_INTENT = "target_intent";
    private static final String START_ACTIVITY = "startActivity";
    private static final String START_SERVICE = "startService";

    public static void hookAMS() throws Exception {
        Object defaultSingleton = null;
        if (Build.VERSION.SDK_INT >= 26) {
            Class<?> aClass = Class.forName("android.app.ActivityManager");
            defaultSingleton = FieldUtil.getField(aClass, null, "IActivityManagerSingleton");
        } else {
            Class<?> aClass = Class.forName("android.app.ActivityManagerNative");
            defaultSingleton = FieldUtil.getField(aClass, null, "gDefault");
        }
        Class<?> singletonClazz = Class.forName("android.util.Singleton");
        Field mInstance = FieldUtil.getField(singletonClazz, "mInstance");
        Object iActivityManager = mInstance.get(defaultSingleton);
        Class<?> iActivityManagerClazz = Class.forName("android.app.IActivityManager");

        IActivityManagerProxy iActivityManagerProxy = new IActivityManagerProxy(iActivityManager, START_ACTIVITY, "", "");
        Object proxyInstance = Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), new Class[]{iActivityManagerClazz}, iActivityManagerProxy);

        mInstance.set(defaultSingleton, proxyInstance);
    }

    public static void hookHandler() throws Exception {
        Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");
        Object currentActivityThread = FieldUtil.getField(activityThreadClazz, null, "sCurrentActivityThread");
        Field mHField = FieldUtil.getField(activityThreadClazz, "mH");
        Handler mH = (Handler) mHField.get(currentActivityThread);
        FieldUtil.setField(activityThreadClazz, "mCallback", mH, new HCallBack(mH));
    }
}
