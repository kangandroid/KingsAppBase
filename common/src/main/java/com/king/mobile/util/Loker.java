package com.king.mobile.util;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class Loker {

    private static final String Tag = "Loker";

    public static void i(String... messages) {

        Log.i(Tag, getString(messages));
    }

    public static void d(String... messages) {
        Log.d(Tag, getString(messages));
    }

    public static void e(String... messages) {
        Log.e(Tag, getString(messages));
    }

    public static void v(String... messages) {
        Log.v(Tag, getString(messages));
    }

    public static void w(String... messages) {
        Log.w(Tag, getString(messages));
    }

    private static String getString(String[] messages) {
        StringBuffer sb = new StringBuffer();
        for (String str : messages) {
            sb.append(str).append(",");
        }
        return sb.toString();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void test(){
        int nThreads = 3;
        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(nThreads);
        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(3);
        ExecutorService workStealingPool = Executors.newWorkStealingPool();
    }
}
