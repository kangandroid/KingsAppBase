package com.king.mobile.util;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题管理
 * 统一更改保存主题设置，
 * 统一管理titleBar的背景色与字体颜色状态栏字体及颜色
 */
public class ThemeManager {
    Theme defaultTheme;
    List<Theme> themes = new ArrayList<>();
    private Theme theme;

    private ThemeManager() {
        defaultTheme = new Theme("default");
        defaultTheme.activeColor = Color.parseColor("#123456");
        defaultTheme.inactiveColor = Color.parseColor("#224466");
        defaultTheme.activityBackgrounColor = Color.parseColor("#EEEEEE");
        defaultTheme.titleBgColor = Color.parseColor("#AAFFFFFF");
        defaultTheme.titleFontColor = Color.parseColor("#333333");
        defaultTheme.navigateBarColor = Color.parseColor("#FFFFFF");
        theme = defaultTheme;
    }

    public static ThemeManager getInstance() {
        return InstanceHolder.instance;
    }

    private static class InstanceHolder {
        private static ThemeManager instance = new ThemeManager();
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public Theme getTheme() {
        return theme;
    }

    public void addTheme(Theme theme) {
        themes.add(theme);
    }

    public class Theme {
        public String name;
        public int titleBgColor;
        public int titleFontColor;
        public int navigateBarColor;
        public int activityBackgrounColor;
        public int activeColor;
        public int inactiveColor;

        public Theme(String name) {
            this.name = name;
        }
    }

    interface OnThemeChangListener {
        void onThemeChange(Theme current, Theme next);
    }
}
