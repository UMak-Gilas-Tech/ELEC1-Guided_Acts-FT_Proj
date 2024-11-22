package com.elecone.guidedexercise;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MachineProblemThree extends BaseActivity {

    private EditText nameInput, prelimInput, midtermInput, finalInput;
    private TextView studentName, semGrade, gradeEquivalent, remarks;
    private Button newEntryButton, computeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp3);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Semestral Grade Computation");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        nameInput = findViewById(R.id.name);
        prelimInput = findViewById(R.id.number1);
        midtermInput = findViewById(R.id.number2);
        finalInput = findViewById(R.id.number3);
        studentName = findViewById(R.id.Studentname);
        semGrade = findViewById(R.id.SemGrade);
        gradeEquivalent = findViewById(R.id.Equivalent);
        remarks = findViewById(R.id.Remarks);
        newEntryButton = findViewById(R.id.new_entry);
        computeButton = findViewById(R.id.compute);

        // New Entry button logic
        newEntryButton.setOnClickListener(v -> {
            new AlertDialog.Builder(MachineProblemThree.this)
                    .setTitle("WARNING MESSAGE")
                    .setMessage("Are you sure?")
                    .setPositiveButton("YES", (dialog, which) -> clearEntries())
                    .setNegativeButton("NO", null)
                    .show();
        });

        // Compute button logic
        computeButton.setOnClickListener(v -> {
            new AlertDialog.Builder(MachineProblemThree.this)
                    .setTitle("WARNING MESSAGE")
                    .setMessage("All Entries Correct?")
                    .setPositiveButton("YES", (dialog, which) -> computeGrade())
                    .setNegativeButton("NO", null)
                    .show();
        });
    }

    private void clearEntries() {
        nameInput.setText("");
        prelimInput.setText("");
        midtermInput.setText("");
        finalInput.setText("");
        studentName.setText("");
        semGrade.setText("");
        gradeEquivalent.setText("");
        remarks.setText("");
        remarks.setTextColor(Color.BLACK);
    }

    private void computeGrade() {

        String name = nameInput.getText().toString();
        String prelim = prelimInput.getText().toString();
        String midterm = midtermInput.getText().toString();
        String finalExam = finalInput.getText().toString();


        if (name.isEmpty() || prelim.isEmpty() || midterm.isEmpty() || finalExam.isEmpty()) {
            showAlert("Please fill all fields");
            return;
        }

        try {
            double prelimGrade = Double.parseDouble(prelim);
            double midtermGrade = Double.parseDouble(midterm);
            double finalGrade = Double.parseDouble(finalExam);
            double semestralGrade = (0.25 * prelimGrade) + (0.25 * midtermGrade) + (0.50 * finalGrade);


            studentName.setText("Student Name: " + name);
            semGrade.setText("Semestral Grade: " + String.format("%.1f", semestralGrade));

            double equivalent;
            String remarkText;

            if (semestralGrade >= 95) {
                equivalent = 1.50;
                remarkText = "Passed";
            } else if (semestralGrade >= 90) {
                equivalent = 2.00;
                remarkText = "Passed";
            } else if (semestralGrade >= 85) {
                equivalent = 2.50;
                remarkText = "Passed";
            } else if (semestralGrade >= 80) {
                equivalent = 3.00;
                remarkText = "Passed";
            } else if (semestralGrade >= 75) {
                equivalent = 3.50;
                remarkText = "Passed";
            } else {
                equivalent = 5.00;
                remarkText = "Failed";
            }

            gradeEquivalent.setText("Pt. Equivalent: " + String.valueOf(equivalent));
            remarks.setText("Remarks: " + remarkText);

            // Change text color based on the pass/fail status
            if (remarkText.equals("Passed")) {
                remarks.setTextColor(Color.BLUE);
            } else {
                remarks.setTextColor(Color.RED);
            }
        } catch (NumberFormatException e) {
            showAlert("Please enter valid numeric values for grades.");
            e.printStackTrace(); // Log the exception for debugging
        }
    }

    private void showAlert(String message) {
        new AlertDialog.Builder(MachineProblemThree.this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }
}
