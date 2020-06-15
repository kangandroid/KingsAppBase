package com.king.mobile.widget;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.LayoutRes;


public abstract class CommonPop extends PopupWindow {


    public CommonPop(Context context) {
        LayoutInflater.from(context);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        setClippingEnabled(true);
        View view = LayoutInflater.from(context).inflate(getLayoutId(), null);
        initView(view);
        setContentView(view);
        setOutsideTouchable(true);
        setTouchable(true);
    }

    protected abstract void initView(View view);

    protected abstract @LayoutRes
    int getLayoutId();


}