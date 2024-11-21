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
                findViewById(R.id.startButton1), findViewById(R.id.startButton2), findViewById(R.id.startButton3),
                findViewById(R.id.startButton4), findViewById(R.id.startButton5), findViewById(R.id.startButton6),
                findViewById(R.id.startButton7), findViewById(R.id.startButton8), findViewById(R.id.startButton9),
                findViewById(R.id.startButton10), findViewById(R.id.startButton11), findViewById(R.id.startButton12),
                findViewById(R.id.startButton13), findViewById(R.id.startButton14), findViewById(R.id.startButton15),
                findViewById(R.id.startButton16), findViewById(R.id.startButton17)
        };

        Class<?>[] activities = {
                GuidedActivityOne.class, GuidedActivityTwo.class, GuidedActivityThree.class,
                GuidedActivityFour.class, GuidedActivityFive.class, GuidedActivitySix.class,
                GuidedActivitySeven.class, GuidedActivityEight.class, GuidedActivityNine.class,
                GuidedActivityTen.class, GuidedActivityEleven.class, GuidedActivityTwelve.class,
                GuidedActivityThirteen.class, GuidedActivityFourteen.class, GuidedActivityFifteen.class,
                GuidedActivitySixteen.class, GuidedActivitySeventeen.class
        };

        String[] activityNames = {
                "Guided Exercise 1", "Guided Exercise 2", "Guided Exercise 3",
                "Guided Exercise 4", "Guided Exercise 5", "Guided Exercise 6",
                "Guided Exercise 7", "Guided Exercise 8", "Guided Exercise 9",
                "Guided Exercise 10", "Guided Exercise 11", "Guided Exercise 12",
                "Guided Exercise 13", "Guided Exercise 14", "Guided Exercise 15",
                "Guided Exercise 16", "Guided Exercise 17"
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