package com.elecone.guidedexercise;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class GuidedActivityTwelve  extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000; // Set the splash screen time to 3 seconds
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_activity_12); // Set the layout for this activity

        splashScreen(); // Call method to display splash screen
    }

    public void splashScreen() {
        // Handler to delay the splash screen for a specified time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // After 3 seconds, navigate to HomeActivity
                intent = new Intent(GuidedActivityTwelve.this, GA12Home.class);
                startActivity(intent); // Start HomeActivity
                finish(); // Close GuidedActivity12 (finish this activity)
            }
        }, SPLASH_TIME_OUT); // 3-second delay before switching to HomeActivity
    }
}
