package com.elecone.guidedexercise;


import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.Manifest;
import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import android.os.Build;


public class GuidedActivitySeventeen extends BaseActivity {
    private static final int NOTIFICATION_PERMISSION_CODE = 1;
    String packageName;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge17_notification_and_broadcast_receiver);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Guided Exercise #17: Notification and Broadcast Receiver");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // Android 13+
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.POST_NOTIFICATIONS},
                        NOTIFICATION_PERMISSION_CODE);
            }
        }

        // Get the package name dynamically
        packageName = getApplicationContext().getPackageName().concat(".");

        // Broadcast a custom intent
        broadcastIntent();

        // Set up a BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);

        // Create and register the receiver
        GuidedActivitySeventeenMyReceiver myReceiver = new GuidedActivitySeventeenMyReceiver();
        registerReceiver(myReceiver, intentFilter);
    }

    public void broadcastIntent() {
        intent = new Intent();
        intent.setAction(packageName + "MY_CUSTOM_ACTION");
        intent.setClass(this, GuidedActivitySeventeenCustomReceiver.class);
        sendBroadcast(intent);
    }
}
