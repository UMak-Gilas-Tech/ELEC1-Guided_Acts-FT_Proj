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
    private GuidedActivitySeventeenMyReceiver myReceiver;
    String packageName;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge17_notification_and_broadcast_receiver);

        // Set action bar title and properties
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Elec1NotificationAndBroadcastReceiver");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Request POST_NOTIFICATIONS permission for Android 13+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
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
        myReceiver = new GuidedActivitySeventeenMyReceiver();
        registerReceiver(myReceiver, intentFilter);
    }

    public void broadcastIntent() {
        intent = new Intent();
        intent.setAction(packageName + "MY_CUSTOM_ACTION");
        intent.setClass(this, GuidedActivitySeventeenCustomReceiver.class);
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the receiver to avoid memory leaks
        if (myReceiver != null) {
            unregisterReceiver(myReceiver);
        }
    }
}
