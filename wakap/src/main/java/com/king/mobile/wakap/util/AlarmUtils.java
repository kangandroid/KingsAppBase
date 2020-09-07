package com.king.mobile.wakap.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.AlarmManagerCompat;

import com.king.mobile.wakap.RepeatingAlarm;
import com.king.mobile.wakap.model.AppInfo;
import com.king.mobile.wakap.model.Task;

import java.util.Calendar;

import static android.app.PendingIntent.FLAG_IMMUTABLE;
import static android.content.Context.ALARM_SERVICE;

public class AlarmUtils {
    public static void setOneShotAlarm(Context context, Task task) {
        AppInfo appInfo = PackageUtils.getAppInfo(task.targetPackageName);
        PendingIntent sender = PendingIntent.getActivity(context, 0, appInfo.launchIntent, FLAG_IMMUTABLE);
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        assert am != null;
        AlarmManagerCompat.setExactAndAllowWhileIdle(am, AlarmManager.RTC_WAKEUP, task.triggerAtMillis, sender);
    }

    public static void setOpenSelf(Context context, long triggerAtMillis) {
        Intent launchIntent = new Intent();
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        launchIntent.setComponent(new ComponentName("com.king.mobile.wakap","MainActivity"));
        PendingIntent sender = PendingIntent.getActivity(context, 0, launchIntent, FLAG_IMMUTABLE);
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

    public static void cancelAlarm(Context context) {
        Intent intent = new Intent(context, RepeatingAlarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        // And cancel the alarm.
        AlarmManager am = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        assert am != null;
        am.cancel(sender);
    }
}
