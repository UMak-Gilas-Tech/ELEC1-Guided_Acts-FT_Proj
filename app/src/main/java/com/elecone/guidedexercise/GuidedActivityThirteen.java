package com.elecone.guidedexercise;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class GuidedActivityThirteen extends BaseActivity  {

    // UI Components
    private EditText studentId, studentName, studentSemGrade;
    private Button addRecord, deleteRecord, editRecord, viewRecord, viewAllRecord;

    // Database components
    private SQLiteDatabase db;
    private Cursor cursor;

    // Helper components
    private AlertDialog.Builder builder;
    private StringBuffer buffer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guided_activity_thirteen);

        // Setting up the ActionBar with a custom title and back button
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("SQLite Demo");
            getSupportActionBar().setHomeAsUpIndicator(android.R.drawable.ic_menu_revert); // Built-in back arrow
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Enable the back button
        }

        init();
        setupListeners();
    }

    // Initialize UI components and database
    private void init() {
        studentId = findViewById(R.id.etStudentID);
        studentName = findViewById(R.id.etStudentName);
        studentSemGrade = findViewById(R.id.etStudentSemGrade);
        addRecord = findViewById(R.id.btnAddRecord);
        deleteRecord = findViewById(R.id.btnDeleteRecord);
        editRecord = findViewById(R.id.btnEditRecord);
        viewRecord = findViewById(R.id.btnViewRecord);
        viewAllRecord = findViewById(R.id.btnViewAllRecord);

        builder = new AlertDialog.Builder(this);

        // Initialize or create the database
        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);

        // Create the student table if it doesn't exist
        db.execSQL("CREATE TABLE IF NOT EXISTS tbl_student (" +
                "student_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "student_name TEXT, " +
                "student_semGrade INTEGER);");

        studentId.setEnabled(true);
        studentName.requestFocus();
    }

    // Setup button listeners
    private void setupListeners() {
        addRecord.setOnClickListener(this::addStudentRecord);
        deleteRecord.setOnClickListener(this::deleteStudentRecord);
        editRecord.setOnClickListener(this::editStudentRecord);
        viewRecord.setOnClickListener(this::viewStudentRecord);
        viewAllRecord.setOnClickListener(this::viewAllStudentRecords);
    }

    // Display a message in a dialog box
    private void displayMessage(String title, String message) {
        builder.setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .show();
    }

    // Clear all input fields
    private void clearEntries() {
        studentName.setText("");
        studentId.setText("");
        studentSemGrade.setText("");
        studentName.requestFocus();
    }

    // Add a student record to the database
    private void addStudentRecord(View v) {
        if (studentName.getText().toString().isEmpty() || studentSemGrade.getText().toString().isEmpty()) {
            displayMessage("Error!", "Please complete the entries.");
            return;
        }

        db.execSQL("INSERT INTO tbl_student(student_name, student_semGrade) VALUES('" +
                studentName.getText().toString() + "', '" +
                studentSemGrade.getText().toString() + "');");
        displayMessage("Information!", "Student record has been successfully added!");
        clearEntries();
    }

    // Delete a student record from the database
    private void deleteStudentRecord(View v) {
        if (studentId.getText().toString().isEmpty()) {
            studentId.setEnabled(true);
            displayMessage("Information!", "Please enter a Student ID.");
            studentId.requestFocus();
            return;
        }

        cursor = db.rawQuery("SELECT * FROM tbl_student WHERE student_id = " +
                Integer.parseInt(studentId.getText().toString()), null);

        if (cursor.moveToFirst()) {
            db.execSQL("DELETE FROM tbl_student WHERE student_id = " +
                    Integer.parseInt(studentId.getText().toString()));
            displayMessage("Information!", "Student record has been successfully deleted!");
        } else {
            displayMessage("Error!", "Invalid Student ID.");
        }

        studentId.setEnabled(false);
        clearEntries();
    }

    // Edit a student record in the database
    private void editStudentRecord(View v) {
        if (studentId.getText().toString().isEmpty()) {
            studentId.setEnabled(true);
            displayMessage("Information!", "Please enter a Student ID.");
            editRecord.setText("SAVE");
            studentId.requestFocus();
            return;
        }

        cursor = db.rawQuery("SELECT * FROM tbl_student WHERE student_id = " +
                Integer.parseInt(studentId.getText().toString()), null);

        if (cursor.moveToFirst()) {
            db.execSQL("UPDATE tbl_student SET student_name = '" +
                    studentName.getText().toString() + "', student_semGrade = '" +
                    studentSemGrade.getText().toString() + "' WHERE student_id = '" +
                    Integer.parseInt(studentId.getText().toString()) + "';");
            displayMessage("Information!", "Student record has been successfully modified!");
        } else {
            displayMessage("Error!", "Invalid Student ID.");
        }

        editRecord.setText("EDIT");
        studentId.setEnabled(false);
        clearEntries();
    }

    // View a single student record
    private void viewStudentRecord(View v) {
        if (studentId.getText().toString().isEmpty()) {
            studentId.setEnabled(true);
            displayMessage("Information!", "Please enter a Student ID.");
            studentId.requestFocus();
            return;
        }

        cursor = db.rawQuery("SELECT * FROM tbl_student WHERE student_id = " +
                Integer.parseInt(studentId.getText().toString()), null);

        if (cursor.moveToFirst()) {
            buffer = new StringBuffer();
            buffer.append("Student ID: ").append(cursor.getString(0)).append("\n");
            buffer.append("Student Name: ").append(cursor.getString(1)).append("\n");
            buffer.append("Student SemGrade: ").append(cursor.getString(2)).append("\n----------");
            displayMessage("Student Record", buffer.toString());
        } else {
            displayMessage("Error!", "Invalid Student ID.");
        }

        studentId.setEnabled(false);
        clearEntries();
    }

    // View all student records
    private void viewAllStudentRecords(View v) {
        cursor = db.rawQuery("SELECT * FROM tbl_student", null);

        if (cursor.getCount() == 0) {
            displayMessage("Information", "No student records found!");
            return;
        }

        buffer = new StringBuffer();
        while (cursor.moveToNext()) {
            buffer.append("Student ID: ").append(cursor.getString(0)).append("\n");
            buffer.append("Student Name: ").append(cursor.getString(1)).append("\n");
            buffer.append("Student SemGrade: ").append(cursor.getString(2)).append("\n----------\n");
        }

        displayMessage("Student Records", buffer.toString());
    }

}
