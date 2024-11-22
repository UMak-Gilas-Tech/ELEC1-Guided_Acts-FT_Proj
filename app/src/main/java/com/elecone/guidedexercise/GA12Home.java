package com.elecone.guidedexercise;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class GA12Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ga12_home);  // Set the layout for GA12Home

        // Example of a button click listener or other UI component actions
        findViewById(R.id.finishButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle completion logic (e.g., navigate to another screen or end the activity)
                finish();  // Example: just finish the activity (return to previous screen)
            }
        });
    }
}
