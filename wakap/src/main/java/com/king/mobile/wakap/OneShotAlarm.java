package com.king.mobile.wakap;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.PowerManager;
import android.util.Log;

import com.king.mobile.util.Loker;
import com.king.mobile.util.ToastUtil;

import java.util.Calendar;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static android.os.PowerManager.ACQUIRE_CAUSES_WAKEUP;
import static android.os.PowerManager.ON_AFTER_RELEASE;
import static android.os.PowerManager.PARTIAL_WAKE_LOCK;

public class OneShotAlarm extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        Loker.d("onReceive---------------------"+calendar.getTime().toString());
//        wakeLock(context);
        ToastUtil.show("onReceive");
        Intent target=new Intent();
        target.addFlags(FLAG_ACTIVITY_NEW_TASK);
        target.setComponent(new ComponentName("com.fjrhw.dev", "com.fengjr.mobile.MainActivity"));
        context.startActivity(target);
    }

    public void wakeLock(Context context){
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        if(pm!=null&&!pm.isInteractive()){//如果是熄灭状态
//            case PARTIAL_WAKE_LOCK:
//            case SCREEN_DIM_WAKE_LOCK:
//            case SCREEN_BRIGHT_WAKE_LOCK:
//            case FULL_WAKE_LOCK:
//            case PROXIMITY_SCREEN_OFF_WAKE_LOCK:
//            case DOZE_WAKE_LOCK:
//            case DRAW_WAKE_LOCK:
            final PowerManager.WakeLock wakeLock = pm.newWakeLock(ACQUIRE_CAUSES_WAKEUP|PARTIAL_WAKE_LOCK,"wakap:wake_lock");
            wakeLock.acquire(10*60*1000L /*10 minutes*/);//亮屏
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    wakeLock.release();//熄灭
                }
            },5*1000);//5秒
        }
    }
}
