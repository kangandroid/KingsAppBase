package com.king.mobile.testapp.daemon;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.RemoteViews;

import com.king.mobile.testapp.MainActivity;
import com.king.mobile.testapp.R;

import static android.app.Notification.DEFAULT_ALL;
import static android.content.Context.NOTIFICATION_SERVICE;

public class TestNotificationWrapper {
    private final int notificationId;
    private final NotificationManager mNM;
    private final Notification.Builder builder;
    private final String channelOneId = "com.king.test.NotificationId";
    private NotificationChannel notificationChannel = null;
    private Context mContext;

    public TestNotificationWrapper(Context context, int notificationId) {
        mContext = context;
        Intent action = new Intent(context, MainActivity.class);
        PendingIntent intent = PendingIntent.getActivity(context, 100, action, 0);
        mNM = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        builder = new Notification.Builder(context)
                .setContentTitle("持续中")
                .setContentText("定位中")
                .setColor(Color.parseColor("#ff6501"))
                .setDefaults(DEFAULT_ALL)
                .setContentIntent(intent)
                .setAutoCancel(false)
                .setOnlyAlertOnce(false)
                .setShowWhen(true)
                .setSmallIcon(R.mipmap.ic_launcher);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notificationChannel = new NotificationChannel(channelOneId, "CHANNEL_ONE_NAME", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.RED);
            notificationChannel.setShowBadge(true);
            notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            assert mNM != null;
            mNM.createNotificationChannel(notificationChannel);
            builder.setChannelId(channelOneId);
        }
        this.notificationId = notificationId;

    }

    public Notification getNotification() {
        return builder.build();
    }

    private void updateRemoteView(){
        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(),R.layout.fragment_test);
        builder.setContent(remoteViews);
//        remoteViews.addView();
    }

    public void updateNotification(int longitude, int latitude) {
        builder.setContentText(new StringBuilder().append("现在位置：（").append(longitude).append(",").append(latitude).append("）").toString());
        notifyNotification();
    }

    public void notifyNotification() {
        Notification build = builder.build();
        mNM.notify(notificationId, build);
    }

    public void cancelNotification() {
        mNM.cancel(notificationId);
    }

    public int getNotificationId() {
        return notificationId;
    }
}




