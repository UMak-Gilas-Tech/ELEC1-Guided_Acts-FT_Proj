package com.elecone.guidedexercise;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up button and activity pairs
        Button[] buttons = {
                findViewById(R.id.startButton12),
                findViewById(R.id.startButton13),
                findViewById(R.id.startButton14),
                findViewById(R.id.startButton15)
        };

        Class<?>[] activities = {
                GuidedActivityTwelve.class,
                GuidedActivityThirteen.class,
                GuidedActivityFourteen.class,
                GuidedActivityFifteen.class
        };

        String[] activityNames = {
                "Guided Exercise 12",
                "Guided Exercise 13",
                "Guided Exercise 14",
                "Guided Exercise 15"
        };

        // Set onClickListeners for all buttons
        for (int i = 0; i < buttons.length; i++) {
            final int index = i; // Capture current index in the loop
            buttons[i].setOnClickListener(v -> launchActivity(activities[index], activityNames[index]));
        }
    }

    // Helper method to launch an activity with error handling
    private void launchActivity(Class<?> activityClass, String activityName) {
        try {
            startActivity(new Intent(MainActivity.this, activityClass));
        } catch (Exception e) {
            Toast.makeText(MainActivity.this, "Error loading " + activityName + ": " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}