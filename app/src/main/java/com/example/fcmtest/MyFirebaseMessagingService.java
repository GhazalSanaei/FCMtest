package com.example.fcmtest;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.util.Log;

import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        NotificationManager notificationManager= (NotificationManager) getSystemService(Service.NOTIFICATION_SERVICE);

       String title= remoteMessage.getNotification().getTitle();
       String body=remoteMessage.getNotification().getBody();



        if (remoteMessage.getData().size()>0)
        {
            String name=remoteMessage.getData().get("name");
            Log.d("msg","name "+name);
        }




        NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
        builder.setContentText(body);
        builder.setContentTitle(title);
        builder.setSmallIcon(R.drawable.notification);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            builder.setChannelId("id");
            NotificationChannel notificationChannel=new NotificationChannel("id","channelName", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(notificationChannel);
        }



        notificationManager.notify(1,builder.build());

    }



    @Override
    public void onNewToken(String token) {
        super.onNewToken(token);
        Log.d("msg","token "+token);
    }
}
