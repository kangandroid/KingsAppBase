package com.king.mobile.lib.pattern.singleton;

public class Sample {
    // 饿汉式
    private static Sample instance;

    private Sample() {
    }

    public static synchronized Sample getInstance() {
        if (instance == null) {
            instance = new Sample();
        }
        return instance;
    }

    //懒汉式
    private static Sample instance2 = new Sample();

    public static Sample getInstance2() {
        return instance2;
    }

    // 静态内部类
    private static class InstanceHolder {
        private static Sample instance3 = new Sample();
    }

    public static Sample getInstance3() {
        return InstanceHolder.instance3;
    }

}
