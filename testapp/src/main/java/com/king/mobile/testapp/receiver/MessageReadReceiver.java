package com.king.mobile.testapp.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.core.app.NotificationManagerCompat;
import android.util.Log;

import com.king.mobile.testapp.service.MyMessagingService;

public class MessageReadReceiver extends BroadcastReceiver {
    private static final String TAG = MessageReadReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (MyMessagingService.READ_ACTION.equals(intent.getAction())) {
          int conversationId = intent.getIntExtra(MyMessagingService.CONVERSATION_ID, -1);
          if (conversationId != -1) {
            Log.d(TAG, "Conversation " + conversationId + " was read");
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
            notificationManager.cancel(conversationId);
            abortBroadcast();
          }
        }
    }
}