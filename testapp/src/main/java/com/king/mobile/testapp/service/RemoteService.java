package com.king.mobile.testapp.service;

import android.app.Service;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.ContentObserver;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RemoteService extends Service {

    private ContentResolver resolver;
    private ContentProviderClient client;
    private MyContentObserver observer;

    @Override
    public void onCreate() {
        super.onCreate();
        resolver = this.getContentResolver();
        client = resolver.acquireContentProviderClient("com.king.android:test");
        Uri uri = Uri.fromParts("com.king.android:test", "adb", "def");
        resolver.notifyChange(uri, null);
        observer = new MyContentObserver(handler);
        resolver.registerContentObserver(uri, true, observer);
    }

    Handler handler = new ObserverHandler();

    static class ObserverHandler extends Handler {

    }

    static class MyContentObserver extends ContentObserver {

        /**
         * Creates a content observer.
         *
         * @param handler The handler to run {@link #onChange} on, or null if none.
         */
        public MyContentObserver(Handler handler) {
            super(handler);
        }

        @Override
        public boolean deliverSelfNotifications() {
            return super.deliverSelfNotifications();
        }

        @Override
        public void onChange(boolean selfChange) {
            super.onChange(selfChange);
        }

        @Override
        public void onChange(boolean selfChange, Uri uri) {
            super.onChange(selfChange, uri);
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        resolver.unregisterContentObserver(observer);
        if (client != null)
            client.release();
    }
}
