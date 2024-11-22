package com.elecone.guidedexercise;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class GuidedActivityEight extends BaseActivity {
    Spinner names;
    TextView result;
    ArrayAdapter<String> adapter;
    String[] listOfNames = {"Name Here", "Papsi", "Pol", "Che", "Tin", "Renz", "Lou", "Chan", "Ven", "Jher"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge8_spinner);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Guided Exercise #8: Spinner");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize components
        names = findViewById(R.id.spnrNamesGE8);
        result = findViewById(R.id.tvResultGE8);

        // Create adapter for the Spinner
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOfNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Attach adapter to Spinner
        names.setAdapter(adapter);
        showSelectedName();
    }

    public void showSelectedName() {
        names.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position > 0) {
                    String selectedName = listOfNames[position];
                    result.setText("Name: " + selectedName);
                    Toast.makeText(getApplicationContext(), "Name: " + selectedName, Toast.LENGTH_SHORT).show();
                } else {
                    result.setText("Name: ");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // No action needed
            }
        });
    }
}
