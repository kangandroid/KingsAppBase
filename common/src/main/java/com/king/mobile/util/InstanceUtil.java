package com.king.mobile.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class InstanceUtil {

    public static <T> T getInstance(Class<T> clazz) {
        return getInstance(clazz, null, null);
    }

    public static <T> T getInstance(Class<T> clazz, Class<?>[] parameterTypes, Object[] arguments) {
        T instance = null;
        Constructor<T> c;
        try {
            if (null == parameterTypes) {
                c = clazz.getDeclaredConstructor((Class[]) null);
                c.setAccessible(true);
                instance = c.newInstance();
            } else {
                c = clazz.getDeclaredConstructor(parameterTypes);
                c.setAccessible(true);
                instance = c.newInstance(arguments);
            }
        } catch (NoSuchMethodException
                | IllegalAccessException
                | InstantiationException
                | IllegalArgumentException
                | InvocationTargetException e) {
            e.printStackTrace();
        }
        if (null == instance) {
            throw new RuntimeException("can't instantiate " + clazz + "whether it is not static");
        }
        return instance;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getInstance(String fullName) {
        Class<T> clazz = null;
        try {
            clazz = (Class<T>) Class.forName(fullName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("class not found " + clazz);
        }
        return getInstance(clazz);
    }

}
