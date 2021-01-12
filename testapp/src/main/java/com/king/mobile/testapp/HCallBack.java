package com.king.mobile.testapp;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import com.king.mobile.testapp.hook.HookHelper;

public class HCallBack implements Handler.Callback {
    public static final int LAUNCH_ACTIVITY = 100;

    public HCallBack(Handler mHandler) {
        this.mHandler = mHandler;
    }

    Handler mHandler;

    @Override
    public boolean handleMessage(@NonNull Message msg) {
        if (msg.what == LAUNCH_ACTIVITY) {
            Object r = msg.obj;
            try {
                Intent intent = (Intent) FieldUtil.getField(r.getClass(), r, "intent");
                Intent target = intent.getParcelableExtra(HookHelper.TARGET_INTENT);
                intent.setComponent(target.getComponent());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mHandler.handleMessage(msg);
        return true;
    }
}
