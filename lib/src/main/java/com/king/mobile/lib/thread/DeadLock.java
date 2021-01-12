package com.king.mobile.lib.thread;

import com.king.mobile.lib.util.PrintUtil;


class DeadLock {
    private int index;
    final Object lockA = new Object();
    final Object lockB = new Object();

    private void test1() {
        synchronized (lockA) {
            PrintUtil.print(Thread.currentThread().getName()+"test1--------------"+index++);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test2();
        }
    }

    private void test2() {
        synchronized (lockB) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            PrintUtil.print(Thread.currentThread().getName()+"test2--------------"+index++);
            test1();
        }
    }

    public static void main(String[] args) {
        final DeadLock deadLock = new DeadLock();
        new Thread(new Runnable() {
            @Override
            public void run() {
                deadLock.test1();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                deadLock.test2();
            }
        }).start();
    }

}
