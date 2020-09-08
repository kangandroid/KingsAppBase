package com.king.mobile.downloadlib.base;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.king.mobile.downloadlib.Announcer;
import com.king.mobile.downloadlib.DownloadListener;

abstract class BaseDownloadView extends FrameLayout implements DownloadListener {
    public BaseDownloadView(@NonNull Context context) {
        super(context);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Announcer.register(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Announcer.unregister(this);
    }
}