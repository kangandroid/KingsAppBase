package com.king.player.util;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.view.Display;

import java.lang.reflect.Method;

/**
 * Author : monkey0928
 * E-mail : hanbao@xwtec.c
 * Date : 2020/6/29 17:54
 * DESCRIBE ：禁用 7.0（23）以上字体大小和显示大小随系统变化
 */
public final class DispUtility {

    /**
     * 禁用7.0（23）以上显示大小改变和文字大小
     */
    public static Resources disabledDisplayDpiChange(Resources res) {
        Configuration newConfig = res.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //字体非默认值
            if (res.getConfiguration().fontScale != 1) {
                newConfig.fontScale = 1;
            }
            newConfig.densityDpi = getDefaultDisplayDensity();
            res.updateConfiguration(newConfig, res.getDisplayMetrics());
        } else {
            //字体非默认值
            if (res.getConfiguration().fontScale != 1) {
                newConfig.fontScale = 1;//设置默认
                res.updateConfiguration(newConfig, res.getDisplayMetrics());
            }
        }
        return res;
    }

    /**
     * 获取手机出厂时默认的densityDpi
     */
    public static int getDefaultDisplayDensity() {
        try {
            Class aClass = Class.forName("android.view.WindowManagerGlobal");
            Method method = aClass.getMethod("getWindowManagerService");
            method.setAccessible(true);
            Object iwm = method.invoke(aClass);
            Method getInitialDisplayDensity = iwm.getClass().getMethod("getInitialDisplayDensity", int.class);
            getInitialDisplayDensity.setAccessible(true);
            Object densityDpi = getInitialDisplayDensity.invoke(iwm, Display.DEFAULT_DISPLAY);
            return (int) densityDpi;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}