package com.yashswi.dilpay.firebase;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;


import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.yashswi.dilpay.Home_screen;
import com.yashswi.dilpay.R;

import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class messagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
//        super.onMessageReceived(remoteMessage);
        sendNotification(remoteMessage);
        Log.e("messagehandle", "From: " + remoteMessage.getFrom());

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d("messagehandle", "Message data payload: " + remoteMessage.getData());

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use WorkManager.
//                scheduleJob();
            } else {
                // Handle message within 10 seconds
//                handleNow();
            }

        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            Log.d("messagehandle", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }
    }

    private void sendNotification(@NotNull RemoteMessage remoteMessage) {
        Map<String, String> data = remoteMessage.getData();

//        String title=remoteMessage.getData().get("user");
//        String content=remoteMessage.getData().get("icon");
        Log.e("sendNoti", data.get("title") + "  " + data.get("content"));
//        String title=data.get("title");
//        String content=data.get("content");
//        String click_action=data.get("click_action");;
        String content = remoteMessage.getNotification().getBody();
        String title = remoteMessage.getNotification().getTitle();
        String click_action = remoteMessage.getNotification().getClickAction();
        Log.e("click action", click_action);
        Intent intent = new Intent(click_action);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
        try {
            NotificationManager noti = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            String NOTIFICATION_CHANNEL = "TESTCHANNEL";
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL, "Dilpay notification", NotificationManager.IMPORTANCE_HIGH);
                notificationChannel.setDescription("TEST channel for dilpay");
                notificationChannel.enableLights(true);
                notificationChannel.setLightColor(Color.BLUE);
                notificationChannel.setVibrationPattern(new long[]{0, 100, 500, 100});
                notificationChannel.enableVibration(true);
                noti.createNotificationChannel(notificationChannel);
            }

            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL);
            notificationBuilder.setAutoCancel(true)
                    .setDefaults(Notification.DEFAULT_ALL)
                    .setWhen(System.currentTimeMillis())
                    .setSmallIcon(R.drawable.rupee)
                    .setTicker("Hearty365")
                    .setContentIntent(pendingIntent)
                    .setContentTitle(title)
                    .setContentText(content)
                    .setContentInfo("info");
            noti.notify(1, notificationBuilder.build());
        } catch (Exception e) {
            Log.e("sendNoti", e.toString());
        }

    }
}
