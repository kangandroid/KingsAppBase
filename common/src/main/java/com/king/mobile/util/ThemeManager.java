package com.king.mobile.util;

import androidx.annotation.ColorRes;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题管理
 * 统一更改保存主题设置，
 * 统一管理titleBar的背景色与字体颜色状态栏字体及颜色
 */
public class ThemeManager {
    List<Theme> themes = new ArrayList<>();
    private Theme theme;

    private ThemeManager() {
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

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public void addTheme(Theme theme){
        themes.add(theme);
    }

    public class Theme {
        public String name;
        public int titleBgColor;
        public int titleFontColor;
        public int navigateBarColor;
        public int activeColor;
        public int inactiveColor;
    }
}
