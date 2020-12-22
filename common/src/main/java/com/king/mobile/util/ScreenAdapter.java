package com.king.mobile.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;

import com.king.mobile.base.BaseApplication;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Objects;

import static android.content.Context.WINDOW_SERVICE;

/**
 * 屏幕适配器,按宽度适配，不同屏幕等比缩放
 */
public class ScreenAdapter {
    private static float mDesignWidth; // 设计图采用屏幕的宽度
    private static float xdpi;

    public static void init(float designWidth) {
        mDesignWidth = designWidth;
    }

    /**
     * 重新计算displayMetrics.xhdpi, 使单位pt重定义为设计稿的相对长度
     *
     * @param resources 上下文
     * @see # activate()
     */
    public static void adaptDensity(Resources resources) {
        if (mDesignWidth == 0) {
            throw new RuntimeException("should call ScreenAdapter.init(designWidth); first");
        }
        if (resources == null) return;
        DisplayMetrics metrics = getMetricsCompat(resources);
        float xdpi = getXdpi();
        if (metrics != null && metrics.xdpi != xdpi) {
            metrics.xdpi = xdpi;
            metrics.ydpi = xdpi;
        }

    }

    private static float getXdpi() {
        if (xdpi == 0) {
            Point size = getScreenSize(BaseApplication.getContext());
            xdpi = size.x / mDesignWidth * 72f;
        }
        return xdpi;
    }

    @NotNull
    private static Point getScreenSize(Context context) {
        Point size = new Point();
        WindowManager windowManager = (WindowManager) Objects.requireNonNull(context.getSystemService(WINDOW_SERVICE));
        windowManager.getDefaultDisplay().getSize(size);
        return size;
    }

    /**
     * 恢复displayMetrics为系统原生状态，单位pt恢复为长度单位磅
     *
     * @param resources 上下文
     * @see # inactivate()
     */
    public static void restoreDensity(Resources resources) {
        resources.getDisplayMetrics().setToDefaults();
        DisplayMetrics metrics = getMetricsCompat(resources);
        if (metrics != null)
            metrics.setToDefaults();
    }

    /**
     * 解决MIUI更改框架导致的MIUI7+Android5.1.1上出现的失效问题(以及极少数基于这部分miui去掉art然后置入xposed的手机)
     */
    private static DisplayMetrics getMetricsCompat(Resources resources) {
//        Resources resources = context.getResources();
        if ("MiuiResources".equals(resources.getClass().getSimpleName())
                || "XResources".equals(resources.getClass().getSimpleName())) {
            try {
                Field field = Resources.class.getDeclaredField("mTmpMetrics");
                field.setAccessible(true);
                return (DisplayMetrics) field.get(resources);
            } catch (Exception e) {
                return null;
            }
        } else {
            return resources.getDisplayMetrics();
        }
    }

    /**
     * 转换pt为px
     *
     * @param context context
     * @param value   需要转换的pt值，若context.resources.displayMetrics经过resetDensity()
     *                的修改则得到修正的相对长度，否则得到原生的磅
     * @return px值
     */
    public static float pt2px(Context context, float value) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PT, value, context.getResources()
                .getDisplayMetrics());
    }
}
