package com.example.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static int NOTIFICATION_ID = 2020;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Support Class for Notification
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setContentTitle(getString(R.string.app_name));
                builder.setContentText("This is Demo Application Notification");

                // notification properties
                builder.setLights(Color.RED, 200, 200);

                long[] vibrate = {100, 500, 100, 500};
                builder.setVibrate(vibrate);

                Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                builder.setSound(soundUri);

                // notification show at lock screen
//                builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
                // notification hide content
                builder.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
                // notification dont show at lock screen
//                builder.setVisibility(NotificationCompat.VISIBILITY_SECRET);

                // For targetting a activity using notification using intent
                Intent intent = new Intent(MainActivity.this, TargetActivity.class);
                intent.putExtra("key", NOTIFICATION_ID);

                // wrape in pending intent
                PendingIntent pendingIntent = PendingIntent.getActivity(
                        MainActivity.this,
                        2020,
                        intent,
                        PendingIntent.FLAG_CANCEL_CURRENT
                );
                builder.setContentIntent(pendingIntent);

                // Recomended Way for Action
                NotificationCompat.Action.Builder actionBuilder = new NotificationCompat.Action.Builder(
                        R.drawable.ic_launcher_background, getString(R.string.app_name), pendingIntent
                );

                NotificationCompat.Action action = actionBuilder.build();
                builder.addAction(action);
                // Not a Recomended Way
                builder.addAction(R.drawable.ic_launcher_background, "Action 2", pendingIntent);

                //for big text style
//                NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
//                bigTextStyle.setBigContentTitle("This is Big Text Title");
//                bigTextStyle.bigText("This text is big and summary text");
//                builder.setStyle(bigTextStyle);

                // big Picture
                NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
                bigPictureStyle.bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.download));
                builder.setStyle(bigPictureStyle);

                // wrape in Notification
                Notification notification = builder.build();


                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                if (manager != null){
                    manager.notify(NOTIFICATION_ID, notification);
                }
                NOTIFICATION_ID++;
            }
        });
    }
}
