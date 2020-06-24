package com.king.mobile.testapp;

import java.lang.reflect.Proxy;

class DynamicProxy {
    public static void main(String[] args) {
        LokerImp lokerImp = new LokerImp();
        ClassLoader classLoader = Loker.class.getClassLoader();
        Class[] classes = {Loker.class};
        ProxyHandler proxyHandler = new ProxyHandler(lokerImp);
        Loker loker = (Loker) Proxy.newProxyInstance(classLoader, classes, proxyHandler);
        loker.log();
    }
}
