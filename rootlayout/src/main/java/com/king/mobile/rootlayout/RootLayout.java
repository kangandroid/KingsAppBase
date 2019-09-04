package com.king.mobile.rootlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class RootLayout extends FrameLayout {
    public RootLayout(@NonNull Context context) {
        super(context);
    }

    public RootLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
}
