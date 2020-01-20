package com.king.mobile.util;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.king.mobile.base.BaseApplication;

import org.w3c.dom.Text;

public class ToastUtil {
    public static void show(String text) {
        Context context = BaseApplication.getContext();
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        TextView view = new TextView(context);
        view.setTextColor(ColorUtil.getColor(context, R.color.white));
        view.setTextSize(14);
        float screenDensity = ScreenUtils.getScreenDensity();
        int h = (int) (screenDensity * 20);
        int v = (int) (screenDensity * 10);
        view.setPadding(h, v, h, v);
        view.setText(text);
        view.setBackground(context.getResources().getDrawable(R.drawable.bg_toast));
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
