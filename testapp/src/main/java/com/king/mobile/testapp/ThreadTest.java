package com.king.mobile.testapp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

class ThreadTest implements Runnable{
    Object object = new Object();

    synchronized void doSomeThing() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void ddd() {
        try {
            synchronized (object) {
                object.wait();
                Thread.sleep(100);

                boolean holdsLock = Thread.holdsLock(object);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized void doOtherThing() {
        notify();
    }

    @Override
    public void run() {
        doSomeThing();
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(100);
    }

}
