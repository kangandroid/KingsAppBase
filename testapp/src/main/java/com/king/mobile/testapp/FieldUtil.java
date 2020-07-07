package com.king.mobile.testapp;

import java.lang.reflect.Field;

public class FieldUtil {
    public static Object getField(Class clazz, Object target, String name) throws Exception {
        Field field = getField(clazz,name);
        field.setAccessible(true);
        return field.get(target);
    }

    public static Field getField(Class clazz, String name) throws NoSuchFieldException {
        Field  field = clazz.getDeclaredField(name);
        field.setAccessible(true);
        return field;
    }

    public static void setField(Class clazz,String name,Object target,Object value) throws Exception {
        Field field = getField(clazz,name);
        field.set(target,value);
    }
}
