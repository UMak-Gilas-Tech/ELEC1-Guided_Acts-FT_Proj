package com.elecone.guidedexercise;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class GuidedActivitySix extends BaseActivity {
    CheckBox one, two, three, four;
    TextView result;
    Button click;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge6_check_box_text_color);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("GuidedActivitySix");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize CheckBoxes, TextView, and Button
        one = findViewById(R.id.chkOne);
        two = findViewById(R.id.chkTwo);
        three = findViewById(R.id.chkThree);
        four = findViewById(R.id.chkFour);

        result = findViewById(R.id.tvResultGE6);
        click = findViewById(R.id.btnClickGE6);

        // Set up the button click behavior
        showResult();
    }

    public void showResult() {
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check conditions and change the TextView color based on the checkbox selections
                if (two.isChecked() && four.isChecked()) {
                    result.setVisibility(View.VISIBLE);
                    result.setTextColor(Color.RED);
                    result.setText("Number Combination Color is RED");
                } else if (one.isChecked() && three.isChecked()) {
                    result.setVisibility(View.VISIBLE);
                    result.setTextColor(Color.BLUE);
                    result.setText("Number Combination Color is BLUE");
                } else if ((one.isChecked() || three.isChecked()) || (two.isChecked() || four.isChecked())) {
                    result.setVisibility(View.VISIBLE);
                    result.setTextColor(Color.GREEN);
                    result.setText("Number Combination Color is GREEN");
                } else {
                    result.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}
