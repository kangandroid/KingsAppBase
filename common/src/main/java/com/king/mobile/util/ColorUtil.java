package com.king.mobile.util;

import android.content.Context;
import android.graphics.Color;

import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;

public class ColorUtil {
    public static final @ColorInt
    int getColor(String color) {
        return Color.parseColor(color);
    }

    public static final @ColorInt
    int getColor(Context context, @ColorRes int color) {
        return context.getResources().getColor(color);
    }
}
