package com.king.mobile.testapp;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class ProxyHandler implements InvocationHandler {
    private Object realObject;

    public ProxyHandler(Object realObject) {
        this.realObject = realObject;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(realObject,args);
        return result;
    }
}
