package com.elecone.guidedexercise;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


    public class GuidedActivityFour extends BaseActivity {


        private static final String DEFAULT_USERNAME = "papsi";
        private static final String DEFAULT_PASSWORD = "android";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_ge4_edit_text_password_type_text_color);

            if (getSupportActionBar() != null) {
                getSupportActionBar().setTitle("EditTextPasswordType and TextColor");
                getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert); // Built-in back arrow
                getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the back button
            }

            // Find views by ID
            EditText etUsername = findViewById(R.id.etUsernameGE4);
            EditText etPassword = findViewById(R.id.etPasswordGE4);
            Button btnLogin = findViewById(R.id.btnLoginGE4);
            TextView tvResult = findViewById(R.id.tvResultGE4);

            // Set button click listener
            btnLogin.setOnClickListener(view -> {
                String inputUsername = etUsername.getText().toString().trim();
                String inputPassword = etPassword.getText().toString().trim();

                if (inputUsername.equals(DEFAULT_USERNAME) && inputPassword.equals(DEFAULT_PASSWORD)) {
                    // Successful login
                    tvResult.setTextColor(Color.GREEN);
                    tvResult.setText("WELCOME " + inputUsername.toUpperCase());
                } else {
                    // Incorrect credentials
                    tvResult.setTextColor(Color.RED);
                    tvResult.setText("USERNAME AND PASSWORD DOES NOT EXIST!");

                    // Restart the transaction after 5 seconds
                    new Handler().postDelayed(() -> {
                        etUsername.setText("");
                        etPassword.setText("");
                        tvResult.setText("");
                    }, 5000); // 5000 ms = 5 seconds
                }
            });
        }
    }
