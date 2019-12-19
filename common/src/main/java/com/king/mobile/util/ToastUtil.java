package com.king.mobile.util;

import android.widget.Toast;

import com.king.mobile.base.BaseApplication;

public class ToastUtil {
    public static void show(String text){
        Toast.makeText(BaseApplication.getContext(),text,Toast.LENGTH_SHORT).show();
    }

}
