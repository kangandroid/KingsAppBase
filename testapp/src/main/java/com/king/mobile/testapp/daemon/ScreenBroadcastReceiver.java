package com.king.mobile.testapp.daemon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.alibaba.android.arouter.launcher.ARouter;

public class ScreenBroadcastReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        // an Intent broadcast.
        String action = intent.getAction();
        if (Intent.ACTION_SCREEN_ON.equals(action)) { // 开屏
            print("ACTION_SCREEN_ON");
            ScreenManager.getInstance().finishAliveActivity();
        } else if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 锁屏
            print("ACTION_SCREEN_OFF");
            Intent i = new Intent(context,KeepAliveActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    void print(String s) {
        System.out.println("ScreenBroadcastReceiver-------------------:" + s);
    }

}