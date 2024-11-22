package com.elecone.guidedexercise;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class GuidedActivityNine extends BaseActivity {
    ListView name;
    ArrayAdapter<String> adapter;
    String[] listOfNames = {"Papsi", "Majoy", "Pol", "Che", "Tin", "Lou", "Renz", "Pet", "Roven", "Chan", "Jher"};
    double[] listOfSemGrades = {1.00, 1.50, 2.00, 1.25, 3.00, 5.00, 1.75, 2.25, 3.00, 1.00, 2.25};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ga9_list_view);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Guided Exercise #9: List View");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        name = findViewById(R.id.lvNameGE9);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listOfNames);
        name.setAdapter(adapter);

        showSemGrade();
    }

    public void showSemGrade() {
        name.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        getApplicationContext(),
                        "Name: " + listOfNames[position] + "\nSem Grade: " + listOfSemGrades[position],
                        Toast.LENGTH_SHORT
                ).show();
            }
        });

    }
}
