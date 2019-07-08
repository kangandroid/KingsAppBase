package com.king.mobile.util;

public class BindLayoutMapping {
    /**
     * 注解解析
     * @param clazz
     * @return
     */
    public static int getLayoutId(Class<?> clazz) {
        BindLayout m = clazz.getAnnotation(BindLayout.class);
        if (m != null) {
            return m.id();
        }
        return 0;
    }
}
