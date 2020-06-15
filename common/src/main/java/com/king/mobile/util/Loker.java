package com.king.mobile.util;

import android.util.Log;

public class Loker {

    private static final String Tag = "Loker";

    public static void i(String... messages) {

        Log.i(Tag, getString(messages));
    }

    public static void d(String... messages) {
        Log.d(Tag, getString(messages));
    }

    public static void e(String... messages) {
        Log.e(Tag, getString(messages));
    }

    public static void v(String... messages) {
        Log.v(Tag, getString(messages));
    }

    public static void w(String... messages) {
        Log.w(Tag, getString(messages));
    }

    private static String getString(String[] messages) {
        StringBuffer sb = new StringBuffer();
        for (String str : messages) {
            sb.append(str).append(",");
        }
        return sb.toString();
    }
}
