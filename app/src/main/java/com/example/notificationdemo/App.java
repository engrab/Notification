package com.example.notificationdemo;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

public class App extends Application {

    public static final String CHANNEL_ONE_ID = "channel_one_id";
    public static final String CHANNEL_TWO_ID = "channel_two_id";

    @Override
    public void onCreate() {
        super.onCreate();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel notificationChannel1 = new NotificationChannel(
                    CHANNEL_ONE_ID,
                    "channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            notificationChannel1.setDescription("This is Audio Notification Channel");

            NotificationChannel notificationChannel2 = new NotificationChannel(
                    CHANNEL_TWO_ID,
                    "channel 2",
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            notificationChannel2.setDescription("This is Video Notification Channel");

            NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel1);
                notificationManager.createNotificationChannel(notificationChannel2);
            }
        }
    }
}
