package com.elecone.guidedexercise;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

public class MachineProblemFour extends BaseActivity {

    // Declare UI elements
    private Spinner employeeIdSpinner;
    private Spinner positionCodeSpinner;
    private Spinner daysWorkedSpinner;
    private TextView employeeNameTextView;
    private RadioGroup civilStatusGroup;
    private Button computeButton;
    private Button clearButton;
    private MachineProblemFour_DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp4);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Employee Payroll Computation");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert); // Built-in back arrow
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the back button
        }

        // Initialize UI elements
        employeeIdSpinner = findViewById(R.id.spinner_employee_id);
        positionCodeSpinner = findViewById(R.id.spinner_position_code);
        daysWorkedSpinner = findViewById(R.id.edit_text_days_worked);
//        daysWorkedSpinner = findViewById(R.id.spinner_days_worked);
        employeeNameTextView = findViewById(R.id.text_employee_name);
        civilStatusGroup = findViewById(R.id.radioGroup_civil_status);
        computeButton = findViewById(R.id.button_compute);
        clearButton = findViewById(R.id.button_clear);

        // Set "Single" as the default civil status
        RadioButton radioSingle = findViewById(R.id.radio_single);
        radioSingle.setChecked(true);

        // Populate the daysWorkedSpinner with values from 1 to 31
        Integer[] days = new Integer[31];
        for (int i = 0; i < days.length; i++) {
            days[i] = i + 1;
        }
        ArrayAdapter<Integer> daysAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, days);
        daysAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daysWorkedSpinner.setAdapter(daysAdapter);
        daysWorkedSpinner.setSelection(20); // Default to 21 days

        // Setup spinners for employee ID and position code
        setupEmployeeIdSpinner();
        setupPositionCodeSpinner();

        // Initialize DatabaseHelper
        dbHelper = new MachineProblemFour_DatabaseHelper(this);

        // Set click listeners for buttons
        computeButton.setOnClickListener(view -> computePayroll());
        clearButton.setOnClickListener(view -> clearFields());
    }

    private void setupEmployeeIdSpinner() {
        String[] employeeIds = {"EMP1201", "EMP1202", "EMP1203", "EMP1204", "EMP1205"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, employeeIds);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        employeeIdSpinner.setAdapter(adapter);

        employeeIdSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedId = employeeIds[position];
                String employeeName = getEmployeeName(selectedId);
                employeeNameTextView.setText(employeeName);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                employeeNameTextView.setText("");
            }
        });
    }

    private String getEmployeeName(String employeeId) {
        switch (employeeId) {
            case "EMP1201": return "Chad";
            case "EMP1202": return "Wojak";
            case "EMP1203": return "Papsi";
            case "EMP1204": return "Baluyut";
            case "EMP1205": return "Siazon";
            default: return "";
        }
    }

    private void setupPositionCodeSpinner() {
        String[] positionCodes = {"A", "B", "C"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, positionCodes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        positionCodeSpinner.setAdapter(adapter);
    }

    private void showAlert(String message) {
        new androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }


    private void computePayroll() {
        // Validate user input before proceeding
        String employeeId = employeeIdSpinner.getSelectedItem() == null ? "" : employeeIdSpinner.getSelectedItem().toString();
        String employeeName = employeeNameTextView.getText().toString();
        String positionCode = positionCodeSpinner.getSelectedItem() == null ? "" : positionCodeSpinner.getSelectedItem().toString();
        int daysWorked;

        try {
            daysWorked = Integer.parseInt(daysWorkedSpinner.getSelectedItem().toString());
        } catch (NumberFormatException e) {
            showAlert("Please select a valid number of days worked.");
            return;
        }

        // Ensure that a civil status is selected
        int selectedRadioButtonId = civilStatusGroup.getCheckedRadioButtonId();
        if (selectedRadioButtonId == -1) {
            showAlert("Please select a Civil Status.");
            return;
        }

        String civilStatus = ((RadioButton) findViewById(selectedRadioButtonId)).getText().toString();

        // Check if the inputs are valid
        if (employeeId.isEmpty() || employeeName.isEmpty() || positionCode.isEmpty()) {
            showAlert("Please fill all required fields.");
            return;
        }

        // Compute payroll details
        double basicPay = computeBasicPay(positionCode, daysWorked);
        double sssContribution = computeSSSContribution(basicPay);
        double withholdingTax = computeWithholdingTax(basicPay, civilStatus);
        double netPay = basicPay - (sssContribution + withholdingTax);

        // Prepare intent for summary activity
        Intent intent = new Intent(this, MachineProblemFour_Summary.class);
        intent.putExtra("employeeId", employeeId);
        intent.putExtra("employeeName", employeeName);
        intent.putExtra("positionCode", positionCode);
        intent.putExtra("civilStatus", civilStatus);
        intent.putExtra("daysWorked", daysWorked);
        intent.putExtra("basicPay", basicPay);
        intent.putExtra("sssContribution", sssContribution);
        intent.putExtra("withholdingTax", withholdingTax);
        intent.putExtra("netPay", netPay);

        // Check if employee ID already exists
        if (dbHelper.employeeIdExists(employeeId)) {
            // Show confirmation dialog to overwrite existing data
            new androidx.appcompat.app.AlertDialog.Builder(this)
                    .setTitle("Confirmation")
                    .setMessage("Employee ID already exists. Do you want to overwrite?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        // Overwrite existing data
                        dbHelper.updateEmployee(employeeId, employeeName, positionCode, civilStatus, daysWorked, basicPay, sssContribution, withholdingTax, netPay);
                        startActivity(intent);
                    })
                    .setNegativeButton("No", null)
                    .show();
        } else {
            // Insert new employee data
            dbHelper.insertEmployee(employeeId, employeeName, positionCode, civilStatus, daysWorked, basicPay, sssContribution, withholdingTax, netPay);
            startActivity(intent);
        }
    }


    private void startSummaryActivity(Intent intent, String employeeId, String employeeName, String positionCode, String civilStatus, int daysWorked, double basicPay, double sssContribution, double withholdingTax, double netPay) {
        intent.putExtra("employeeId", employeeId);
        intent.putExtra("employeeName", employeeName);
        intent.putExtra("positionCode", positionCode);
        intent.putExtra("civilStatus", civilStatus);
        intent.putExtra("daysWorked", daysWorked);
        intent.putExtra("basicPay", basicPay);
        intent.putExtra("sssContribution", sssContribution);
        intent.putExtra("withholdingTax", withholdingTax);
        intent.putExtra("netPay", netPay);
        startActivity(intent);
    }

    private double computeBasicPay(String positionCode, int daysWorked) {
        double ratePerDay;
        switch (positionCode) {
            case "A": ratePerDay = 500; break;
            case "B": ratePerDay = 400; break;
            case "C": ratePerDay = 300; break;
            default: ratePerDay = 0;
        }
        return ratePerDay * daysWorked;
    }

    private double computeSSSContribution(double basicPay) {
        double sssRate;
        if (basicPay >= 10000) {
            sssRate = 0.07; // 7%
        } else if (basicPay >= 5000) {
            sssRate = 0.05; // 5%
        } else if (basicPay >= 1000) {
            sssRate = 0.03; // 3%
        } else {
            sssRate = 0.01; // 1%
        }
        return basicPay * sssRate;
    }

    private double computeWithholdingTax(double basicPay, String civilStatus) {
        double taxRate;
        switch (civilStatus) {
            case "Single": taxRate = 0.10; break; // 10%
            case "Married":
            case "Widowed": taxRate = 0.05; break; // 5%
            default: taxRate = 0.0; // Default for others
        }
        return basicPay * taxRate;
    }

    private void clearFields() {
        employeeIdSpinner.setSelection(0);
        employeeNameTextView.setText("");
        positionCodeSpinner.setSelection(0);
        civilStatusGroup.clearCheck();
        civilStatusGroup.check(R.id.radio_single);
        daysWorkedSpinner.setSelection(20); // Reset to default 21 days
    }
}
