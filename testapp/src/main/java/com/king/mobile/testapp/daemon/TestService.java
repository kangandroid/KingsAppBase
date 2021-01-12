package com.king.mobile.testapp.daemon;

import android.app.Service;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

import androidx.annotation.NonNull;

import com.king.mobile.testapp.AidlTestInterface;
import com.king.mobile.testapp.service.Location;
import com.king.mobile.testapp.service.MyMessagingService;


public class TestService extends Service {

    private TestNotificationWrapper notificationWrapper;
    private Location.LocationListener updateNotification = (longitude, latitude) -> {
        Message obtain = Message.obtain();
    };

    public TestService() {
    }


    @Override
    public void onCreate() {
        super.onCreate();
        print("onCreate");

    }

    static void print(String s) {
        System.out.println("TestService-------------------:" + s);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        print("onStartCommand");
        notificationWrapper = new TestNotificationWrapper(this, 100);
        notificationWrapper.notifyNotification();
        Location.start(updateNotification);
        startForeground(notificationWrapper.getNotificationId(), notificationWrapper.getNotification());// 提升为前台进程
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        print("onDestroy");
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        print("onConfigurationChanged");
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        print("onLowMemory");
    }

    @Override
    public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        print("onTrimMemory");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        boolean b = super.onUnbind(intent);
        print("onUnbind");
        // 所有客户端都unBinderService的时候会调用
        // 默认返回false 返回true 表示暂不销毁 有新的client绑定时时不需要重新创建
        // 会执行 onRebind
        return true;
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        print("onRebind");
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        super.onTaskRemoved(rootIntent);
        print("onTaskRemoved");
    }

    private final AidlTestInterface.Stub binder = new AidlTestInterface.Stub() {
        private DeathRecipient deathRecipient;


        @Override
        public String getAnswer(String aString) {
            print(aString);
            return "你哈";
        }




    };

    @Override
    public IBinder onBind(Intent intent) {
        print("onBind");
        return binder;
    }
}