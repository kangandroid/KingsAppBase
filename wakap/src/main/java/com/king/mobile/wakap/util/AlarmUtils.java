package com.king.mobile.wakap.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.AlarmManagerCompat;

import com.king.mobile.util.Loker;
import com.king.mobile.wakap.RepeatingAlarm;
import com.king.mobile.wakap.model.AppInfo;
import com.king.mobile.wakap.model.Task;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;
import static android.app.PendingIntent.FLAG_IMMUTABLE;
import static android.content.Context.ALARM_SERVICE;

public class AlarmUtils {

    private static int selfCode = 100;
    public static void setOneShotAlarm(Context context, Task task) {
        try {
            AppInfo appInfo = PackageUtils.getAppInfo(task.targetPackageName);
            PendingIntent sender = PendingIntent.getActivity(context, task.id, appInfo.launchIntent, FLAG_IMMUTABLE);
            AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
            assert am != null;
            AlarmManagerCompat.setAndAllowWhileIdle(am, AlarmManager.RTC_WAKEUP, task.triggerAtMillis, sender);
        } catch (Exception e) {
            Loker.d("---------------" + e.getMessage());
        }

    }

    public static void setOpenSelf(Context context, long triggerAtMillis) {
        selfCode++;
        AppInfo appInfo = PackageUtils.getAppInfo("com.king.mobile.wakap");
        PendingIntent sender = PendingIntent.getActivity(context, selfCode, appInfo.launchIntent, FLAG_IMMUTABLE);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        assert am != null;
        AlarmManagerCompat.setExactAndAllowWhileIdle(am, AlarmManager.RTC_WAKEUP, triggerAtMillis, sender);
    }


    public static void setRepeatAlarm(Context context) {
        Intent intent = new Intent(context, RepeatingAlarm.class);
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        // We want the alarm to go off 10 seconds from now.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);
        // Schedule the alarm!
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        assert am != null;
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 10 * 1000, sender);

    }

    public static void cancelAlarm(Context context, Task task) {
        AppInfo appInfo = PackageUtils.getAppInfo(task.targetPackageName);
        PendingIntent sender = PendingIntent.getBroadcast(context, task.id, appInfo.launchIntent, FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        assert am != null;
        am.cancel(sender);
    }
}
