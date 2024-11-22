package com.elecone.guidedexercise;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GuidedActivityOne extends BaseActivity {

    EditText etName, etAge;
    TextView tvResultName, tvResultAge;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge1_edit_text_text_view_button);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("FirstGuidedExercise");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert); // Built-in back arrow
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the back button
        }

        etName = findViewById(R.id.etNameGE1);
        etAge = findViewById(R.id.etAgeGE1);
        tvResultName = findViewById(R.id.tvResultNameGE1);
        tvResultAge = findViewById(R.id.tvResultAgeGE1);
        btnSubmit = findViewById(R.id.btnClickGE1);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();
                String age = etAge.getText().toString().trim();

                if (!name.isEmpty() && !age.isEmpty()) {
                    tvResultName.setText("Name: " + name);
                    tvResultAge.setText("Age: " + age);
                } else {
                    tvResultName.setText("Name: ");
                    tvResultAge.setText("Age: ");
                }

                etName.setText("");
                etAge.setText("");
                etName.requestFocus();
            }
        });
    }
}
