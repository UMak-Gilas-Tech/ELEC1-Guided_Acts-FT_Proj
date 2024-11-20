package com.elecone.guidedexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set onClickListener for Guided Exercise 12 button
        findViewById(R.id.startButton12).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchActivity(GuidedActivityTwelve.class, "Guided Exercise 12");
            }
        });

        // Set onClickListener for Guided Exercise 13 button
        findViewById(R.id.startButton13).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                launchActivity(GuidedActivity13.class, "Guided Exercise 13");
            }
        });

        // Set onClickListener for Guided Exercise 14 button
        findViewById(R.id.startButton14).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                launchActivity(GuidedActivity14.class, "Guided Exercise 14");
            }
        });

        // Set onClickListener for Guided Exercise 15 button
        findViewById(R.id.startButton15).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                launchActivity(GuidedActivity15.class, "Guided Exercise 15");
            }
        });
    }

    // Helper method to launch an activity with error handling
    private void launchActivity(Class<?> activityClass, String activityName) {
        try {
            // Start the activity
            startActivity(new Intent(MainActivity.this, activityClass));
        } catch (Exception e) {
            // Show error toast if activity cannot be started
            Toast.makeText(MainActivity.this, "Error loading " + activityName + ": " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
