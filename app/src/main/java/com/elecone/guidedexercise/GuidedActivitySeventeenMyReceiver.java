package com.elecone.guidedexercise;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;

public class GuidedActivitySeventeenMyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state;
        switch (intent.getAction()){
            case Intent.ACTION_BATTERY_CHANGED:
                state = intent.getAction();
                showNotification("Battery Info",state,context);
                break;
            case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                state = intent.getAction();
                showNotification("Airplane Mode",state,context);
                break;
            default:
        }
    }

    public void showNotification(String title, String state, Context context){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission is not granted, exit or handle accordingly
                return;
            }
        }

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel("mnc","mn",NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context,"mnc")
                .setContentTitle(title)
                .setContentText(state)
                .setSmallIcon(R.drawable.ic_announcement)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(context,0,
                        new Intent(context,MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT))
                .setDefaults(Notification.FLAG_ONLY_ALERT_ONCE);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0,builder.build());

        final MediaPlayer mediaPlayer = MediaPlayer.create(context,R.raw.alert);
        mediaPlayer.start();
    }


}
