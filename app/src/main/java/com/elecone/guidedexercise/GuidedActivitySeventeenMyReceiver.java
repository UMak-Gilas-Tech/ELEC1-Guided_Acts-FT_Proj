package com.elecone.guidedexercise;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.app.PendingIntent;

public class GuidedActivitySeventeenMyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        try {
            String state = intent.getAction();
            if (state != null) {
                switch (state) {
                    case Intent.ACTION_BATTERY_CHANGED:
                        showNotification("Battery Info", "Battery status updated", context);
                        break;
                    case Intent.ACTION_AIRPLANE_MODE_CHANGED:
                        boolean isAirplaneModeOn = android.provider.Settings.Global.getInt(
                                context.getContentResolver(),
                                android.provider.Settings.Global.AIRPLANE_MODE_ON, 0) != 0;
                        String modeStatus = isAirplaneModeOn ? "Airplane Mode On" : "Airplane Mode Off";
                        showNotification("Airplane Mode", modeStatus, context);
                        break;
                    default:
                        // Handle other intents if necessary
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log unexpected errors
        }
    }

    private void showNotification(String title, String state, Context context) {
        // Check for POST_NOTIFICATIONS permission (Android 13+)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                // Exit gracefully if the permission is not granted
                return;
            }
        }

        // Create NotificationChannel for Android O+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(
                    "mnc", // Channel ID
                    "My Notifications", // Channel Name
                    NotificationManager.IMPORTANCE_DEFAULT
            );
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        // Build and display the notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "mnc")
                .setContentTitle(title)
                .setContentText(state)
                .setSmallIcon(R.drawable.ic_announcement)
                .setAutoCancel(true)
                .setContentIntent(PendingIntent.getActivity(
                        context, 0,
                        new Intent(context, MainActivity.class),
                        PendingIntent.FLAG_IMMUTABLE // Use FLAG_IMMUTABLE for Android 12+
                ))
                .setDefaults(NotificationCompat.DEFAULT_ALL);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(0, builder.build());

        // Play the alert sound if available
        playAlertSound(context);
    }

    private void playAlertSound(Context context) {
        // Ensure the alert.mp3 file exists in res/raw directory
        MediaPlayer mediaPlayer = MediaPlayer.create(context, R.raw.alert);
        if (mediaPlayer != null) {
            mediaPlayer.start();
            mediaPlayer.setOnCompletionListener(MediaPlayer::release); // Release resources after playback
        } else {
            // Log or handle the issue if the file is missing
            System.out.println("Error: MediaPlayer is null, check if the alert.mp3 file exists.");
        }
    }
}
