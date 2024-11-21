package com.elecone.guidedexercise;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.graphics.PorterDuff;

public class GuidedActivityThree extends BaseActivity{
    EditText num1, num2;
    Button sum, ave;
    Toast toast;
    View view;
    double firstNum = 0, secondNum = 0, total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge3_simple_computation);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Simple Computation");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert); // Built-in back arrow
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the back button
        }

        num1 = findViewById(R.id.etNum1GE3);
        num2 = findViewById(R.id.etNum2GE3);
        sum = findViewById(R.id.btnSumGE3);
        ave = findViewById(R.id.btnAveGE3);

        computeTotal();
    }

    public void computeTotal(){
        sum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if either of the EditText fields is empty or contains non-numeric input
                if(TextUtils.isEmpty(num1.getText()) || TextUtils.isEmpty(num2.getText())){
                    displayErrorMessage("Please enter both numbers.");
                } else {
                    try {
                        firstNum = Double.parseDouble(num1.getText().toString());
                        secondNum = Double.parseDouble(num2.getText().toString());
                        total = firstNum + secondNum;
                        Toast.makeText(getApplicationContext(), "SUM: " + total, Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        displayErrorMessage("Please enter valid numbers.");
                    }
                }
            }
        });

        ave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Check if either of the EditText fields is empty or contains non-numeric input
                if(TextUtils.isEmpty(num1.getText()) || TextUtils.isEmpty(num2.getText())){
                    displayErrorMessage("Please enter both numbers.");
                } else {
                    try {
                        firstNum = Double.parseDouble(num1.getText().toString());
                        secondNum = Double.parseDouble(num2.getText().toString());
                        total = (firstNum + secondNum) / 2;
                        Toast.makeText(getApplicationContext(), "AVE: " + total, Toast.LENGTH_SHORT).show();
                    } catch (NumberFormatException e) {
                        displayErrorMessage("Please enter valid numbers.");
                    }
                }
            }
        });
    }

    public void displayErrorMessage(String message){
        toast = Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT);
        view = toast.getView();
        if (view != null) {
            view.getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
