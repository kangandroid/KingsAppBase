package com.king.mobile.util;

import androidx.annotation.ColorRes;

/**
 * 主题管理
 * 统一更改保存主题设置，
 * 统一管理titleBar的背景色与字体颜色状态栏字体及颜色
 *
 *
 *
 */
public class ThemeManager {
    private ThemeManager(){

    }

    @ColorRes
    private int primaryColor = R.color.colorAccent;

    public static int getPrimarColor() {
        return getInstance().primaryColor;
    }

    public static ThemeManager getInstance() {
        return IntanceHolder.instance;
    }

    static class IntanceHolder {
        private static ThemeManager instance = new ThemeManager();
    }
}
