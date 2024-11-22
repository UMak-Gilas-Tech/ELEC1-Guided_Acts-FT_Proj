package com.elecone.guidedexercise;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.core.content.ContextCompat;

public class MachineProblemTwo extends BaseActivity {

    // UI components
    private EditText etFirstNumber, etSecondNumber;
    private TextView tvResult;
    private Button btnAdd, btnDiff, btnProd, btnQuo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mp2);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Simple Calculator Computation");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        // Initialize UI elements
        etFirstNumber = findViewById(R.id.etFirstNumber);
        etSecondNumber = findViewById(R.id.etSecondNumber);
        tvResult = findViewById(R.id.tvResult);
        btnAdd = findViewById(R.id.btnAdd);
        btnDiff = findViewById(R.id.btnDiff);
        btnProd = findViewById(R.id.btnProd);
        btnQuo = findViewById(R.id.btnQuo);

        // Set button click listeners
        btnAdd.setOnClickListener(v -> performOperation('+'));
        btnDiff.setOnClickListener(v -> performOperation('-'));
        btnProd.setOnClickListener(v -> performOperation('*'));
        btnQuo.setOnClickListener(v -> performOperation('/'));
    }

    /**
     * Perform the selected operation based on the operator.
     */
    private void performOperation(char operator) {
        String firstNumberStr = etFirstNumber.getText().toString().trim();
        String secondNumberStr = etSecondNumber.getText().toString().trim();

        if (firstNumberStr.isEmpty() || secondNumberStr.isEmpty()) {
            tvResult.setText("Please enter valid numbers");
            tvResult.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
            return;
        }

        try {
            double num1 = Double.parseDouble(firstNumberStr);
            double num2 = Double.parseDouble(secondNumberStr);
            double result;
            String output;

            switch (operator) {
                case '+':
                    result = num1 + num2;
                    output = "Total SUM is: ";
                    break;
                case '-':
                    result = num1 - num2;
                    output = "Total DIFF is: ";
                    break;
                case '*':
                    result = num1 * num2;
                    output = "Total PROD is: ";
                    break;
                case '/':
                    if (num2 == 0) {
                        tvResult.setText("Division by zero is undefined");
                        tvResult.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
                        return;
                    }
                    result = num1 / num2;
                    output = "Total QUO is: ";
                    break;
                default:
                    tvResult.setText("Unknown operation");
                    return;
            }

            tvResult.setText(output + result);

            // Change text color based on the parity of the rounded result
            int color = (Math.round(result) % 2 == 1) ?
                    ContextCompat.getColor(this, android.R.color.holo_red_dark) :
                    ContextCompat.getColor(this, android.R.color.holo_blue_dark);
            tvResult.setTextColor(color);

        } catch (NumberFormatException e) {
            tvResult.setText("Invalid number format");
            tvResult.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark));
        }
    }
}
