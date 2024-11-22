package com.elecone.guidedexercise;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GuidedActivityTwo extends BaseActivity {

    EditText etNameGE2;
    Button btnClickGE2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge2_toast);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Guided Exercise #2: Toast");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert); // Built-in back arrow
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the back button
        }

        etNameGE2 = findViewById(R.id.etNameGE2);
        btnClickGE2 = findViewById(R.id.btnClickGE2);

        btnClickGE2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etNameGE2.getText().toString().trim();
                if (!name.isEmpty()) {
                    LayoutInflater inflater = getLayoutInflater();
                    View layout = inflater.inflate(R.layout.activity_ge2_custom_toast, null);

                    TextView toastText = layout.findViewById(R.id.toastText);
                    toastText.setText("Hello " + name + "!\nWelcome to Android Development!");

                    Toast toast = new Toast(getApplicationContext());
                    toast.setDuration(Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.BOTTOM, 0, 150);
                    toast.setView(layout);
                    toast.show();
                } else {
                    Toast.makeText(GuidedActivityTwo.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
