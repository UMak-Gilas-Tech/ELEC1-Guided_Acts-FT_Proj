package com.elecone.guidedexercise;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MachineProblemFour_Summary extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp4_summary_activity);
        Log.d("SummaryActivity", "onCreate called");

        // data retrieval
        Intent intent = getIntent();
        String employeeId = intent.getStringExtra("employeeId");
        String employeeName = intent.getStringExtra("employeeName");
        String positionCode = intent.getStringExtra("positionCode");
        String civilStatus = intent.getStringExtra("civilStatus");
        int daysWorked = intent.getIntExtra("daysWorked", 0);
        double basicPay = intent.getDoubleExtra("basicPay", 0);
        double sssContribution = intent.getDoubleExtra("sssContribution", 0);
        double withholdingTax = intent.getDoubleExtra("withholdingTax", 0);
        double netPay = intent.getDoubleExtra("netPay", 0);

        // textview find display sum
        TextView textSummaryEmployeeId = findViewById(R.id.text_summary_employee_id);
        TextView textSummaryName = findViewById(R.id.text_summary_name);
        TextView textSummaryPositionCode = findViewById(R.id.text_summary_position_code);
        TextView textSummaryCivilStatus = findViewById(R.id.text_summary_civil_status);
        TextView textSummaryDaysWorked = findViewById(R.id.text_summary_days_worked);
        TextView textSummaryBasicPay = findViewById(R.id.text_summary_basic_pay);
        TextView textSummarySSSContribution = findViewById(R.id.text_summary_sss_contribution);
        TextView textSummaryWithholdingTax = findViewById(R.id.text_summary_withholding_tax);
        TextView textSummaryNetPay = findViewById(R.id.text_summary_net_pay);

        // deci 4 currency
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        // null validation for erros
        textSummaryEmployeeId.setText(employeeId != null ? employeeId : "N/A");
        textSummaryName.setText(employeeName != null ? employeeName : "N/A");
        textSummaryPositionCode.setText(positionCode != null ? positionCode : "N/A");
        textSummaryCivilStatus.setText(civilStatus != null ? civilStatus : "N/A");
        textSummaryDaysWorked.setText(String.valueOf(daysWorked));  // Convert int to String
        textSummaryBasicPay.setText("Php " + decimalFormat.format(basicPay));
        textSummarySSSContribution.setText("Php " + decimalFormat.format(sssContribution));
        textSummaryWithholdingTax.setText("Php " + decimalFormat.format(withholdingTax));
        textSummaryNetPay.setText("Php " + decimalFormat.format(netPay));

        // Back button listener
        Button buttonBack = findViewById(R.id.button_back);
        buttonBack.setOnClickListener(v -> onBackPressed());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed(); // Handle back button press
        Log.d("SummaryActivity", "Back button pressed");
    }
}
