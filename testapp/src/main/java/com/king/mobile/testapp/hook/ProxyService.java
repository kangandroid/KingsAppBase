package com.king.mobile.testapp.hook;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import com.king.mobile.testapp.FieldUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ProxyService extends Service {
    public static final String TARGET_SERVICE = "target_service";

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (null == intent || !intent.hasExtra(TARGET_SERVICE)) {
            return START_STICKY;
        }
        String servicesName = intent.getStringExtra(TARGET_SERVICE);
        if (TextUtils.isEmpty(servicesName)) {
            return START_STICKY;
        }
        Service targetService = null;
        try {
            Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");
            Method getApplicationThreadMethod = activityThreadClazz.getDeclaredMethod("getApplicationThread");
            getApplicationThreadMethod.setAccessible(true);
            Object activityThread = FieldUtil.getField(activityThreadClazz, null, "sCurrentActivityThread");
            Object applicationThread = getApplicationThreadMethod.invoke(activityThread);

            Class<?> iInterfaceClazz = Class.forName("android.os.IInterface");
            Method asBinderMethod = iInterfaceClazz.getDeclaredMethod("asBinder");
            asBinderMethod.setAccessible(true);

            Object token = asBinderMethod.invoke(applicationThread);
            Class<?> serviceClazz = Class.forName("android.app.Service");
            Method attachMethod = serviceClazz.getDeclaredMethod("attach", Context.class, activityThreadClazz, String.class, IBinder.class, Application.class, Object.class);
            attachMethod.setAccessible(true);

            Object defaultSingleton = null;
            if(Build.VERSION.SDK_INT>=26){
                Class<?> activityManagerClazz = Class.forName("android.app.ActivityManager");
                defaultSingleton = FieldUtil.getField(activityManagerClazz, null, "IActivityManagerSingleton");
            } else {
                Class<?> activityManagerNativeClazz = Class.forName("android.app.ActivityManagerNative");
                defaultSingleton = FieldUtil.getField(activityManagerNativeClazz, null, "gDefault");
            }

            Class<?> singletonClazz = Class.forName("android.util.Singleton");
            Field mInstanceField = FieldUtil.getField(serviceClazz, "mInstance");
            Object iActivityManager = mInstanceField.get(defaultSingleton);
            targetService = (Service) Class.forName(servicesName).newInstance();
            attachMethod.invoke(targetService,this,activityThread,intent.getComponent().getClassName(),token,getApplication());
            targetService.onCreate();
        } catch (Exception e) {
            e.printStackTrace();
            return START_STICKY;
        }
        return START_STICKY;
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
