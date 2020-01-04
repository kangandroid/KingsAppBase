package com.king.mobile.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor {

    private ExecutorService fixedThreadPool;

    private Executor() {
        int count = Runtime.getRuntime().availableProcessors();
        Loker.d(String.format("available processor count $d", count));
        fixedThreadPool = Executors.newFixedThreadPool(count);
    }

    static class InstanceHolder {
        public static Executor executor = new Executor();
    }


    public static Executor getInstance() {
        return InstanceHolder.executor;
    }


    public void execute(Runnable task) {
        if (task == null) return;
        fixedThreadPool.execute(task);
    }

}
