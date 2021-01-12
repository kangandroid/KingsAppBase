package com.king.mobile.testapp.utils;

import android.os.Bundle;

public class StartMonitor {

    private long start;

    private StartMonitor() {

    }

    public void startCompleted() {
        long end = System.currentTimeMillis();
        LogUtil.print("StartMonitor", "启动时长=" + (end - start) + "ms");
    }

    public void startBegin() {
        start = System.currentTimeMillis();
    }

    private static class InstanceHolder {
        static StartMonitor instance = new StartMonitor();
    }

    public static StartMonitor getInstance() {
        return InstanceHolder.instance;
    }


}
