package com.elecone.guidedexercise;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

public class GuidedActivityFive extends BaseActivity {

    RadioButton red, blue, yellow, green;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ga5_radio_button_radio_group_bgcolor);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("RadioGroup, RadioButton and Background Color");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }


        red = findViewById(R.id.rbRed);
        blue = findViewById(R.id.rbBlue);
        yellow = findViewById(R.id.rbYellow);
        green = findViewById(R.id.rbGreen);
    }

    public void showSelectedColor() {
        if (red.isChecked()) {
            Toast.makeText(getApplicationContext(), "Color: RED", Toast.LENGTH_SHORT).show();
        }
        if (blue.isChecked()) {
            Toast.makeText(getApplicationContext(), "Color: BLUE", Toast.LENGTH_SHORT).show();
        }
        if (yellow.isChecked()) {
            Toast.makeText(getApplicationContext(), "Color: YELLOW", Toast.LENGTH_SHORT).show();
        }
        if (green.isChecked()) {
            Toast.makeText(getApplicationContext(), "Color: GREEN", Toast.LENGTH_SHORT).show();
        }
    }

    // call this method on onClick property of our four radio button
    public void changeBackgroundColor(View view) {
        // this will set into true if one of the radio buttons is checked
        boolean checked = ((RadioButton) view).isChecked();

        if (checked) {
            if (view.getId() == R.id.rbRed) {
                getWindow().getDecorView().setBackgroundColor(Color.RED);
                showSelectedColor();
            } else if (view.getId() == R.id.rbBlue) {
                getWindow().getDecorView().setBackgroundColor(Color.BLUE);
                showSelectedColor();
            } else if (view.getId() == R.id.rbYellow) {
                getWindow().getDecorView().setBackgroundColor(Color.YELLOW);
                showSelectedColor();
            } else if (view.getId() == R.id.rbGreen) {

                getWindow().getDecorView().setBackgroundColor(Color.GREEN);
                showSelectedColor();
            }
        }
    }
}
