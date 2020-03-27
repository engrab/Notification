package com.example.notificationdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SecondActivity extends AppCompatActivity {

    EditText editTextTitle, editTextMessage;
    NotificationManager manager;
    String title;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        editTextTitle = findViewById(R.id.editTextTitle);
        editTextMessage = findViewById(R.id.editTextMessage);

        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);


    }

    public void firstChannel(View view) {


        title = editTextTitle.getText().toString();
        message = editTextMessage.getText().toString();


        NotificationCompat.Builder builder = new NotificationCompat.Builder(SecondActivity.this,
                App.CHANNEL_ONE_ID);
        builder.setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(R.drawable.ic_notification1)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();

        manager.notify(1, builder.build());

    }

    public void secondChannel(View view) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(SecondActivity.this, App.CHANNEL_TWO_ID);
        builder.setSmallIcon(R.drawable.ic_notification2)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
        manager.notify(2, builder.build());

    }
}
