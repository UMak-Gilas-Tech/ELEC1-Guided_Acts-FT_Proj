package com.elecone.guidedexercise;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MachineProblemFour_DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "employeePayroll.db";
    private static final int DATABASE_VERSION = 1;

    // Table + col names
    private static final String TABLE_EMPLOYEE = "employee";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_EMPLOYEE_ID = "employeeId";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_POSITION = "position";
    private static final String COLUMN_CIVIL_STATUS = "civilStatus";
    private static final String COLUMN_DAYS_WORKED = "daysWorked";
    private static final String COLUMN_BASIC_PAY = "basicPay";
    private static final String COLUMN_SSS_CONTRIBUTION = "sssContribution";
    private static final String COLUMN_WITHHOLDING_TAX = "withholdingTax";
    private static final String COLUMN_NET_PAY = "netPay";

    public MachineProblemFour_DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_EMPLOYEE + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_EMPLOYEE_ID + " TEXT UNIQUE, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_POSITION + " TEXT, " +
                COLUMN_CIVIL_STATUS + " TEXT, " +
                COLUMN_DAYS_WORKED + " INTEGER, " +
                COLUMN_BASIC_PAY + " REAL, " +
                COLUMN_SSS_CONTRIBUTION + " REAL, " +
                COLUMN_WITHHOLDING_TAX + " REAL, " +
                COLUMN_NET_PAY + " REAL" +
                ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        onCreate(db);
    }

    // Method to insert employee data
    public boolean insertEmployee(String employeeId, String name, String position, String civilStatus,
                                  int daysWorked, double basicPay, double sssContribution,
                                  double withholdingTax, double netPay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_EMPLOYEE_ID, employeeId);
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_POSITION, position);
        contentValues.put(COLUMN_CIVIL_STATUS, civilStatus);
        contentValues.put(COLUMN_DAYS_WORKED, daysWorked);
        contentValues.put(COLUMN_BASIC_PAY, basicPay);
        contentValues.put(COLUMN_SSS_CONTRIBUTION, sssContribution);
        contentValues.put(COLUMN_WITHHOLDING_TAX, withholdingTax);
        contentValues.put(COLUMN_NET_PAY, netPay);

        // Insert the new row and return success
        long result = db.insert(TABLE_EMPLOYEE, null, contentValues);
        return result != -1; // Returns true if the row was inserted, false otherwise
    }

    // Method to update employee data
    public boolean updateEmployee(String employeeId, String name, String position, String civilStatus,
                                  int daysWorked, double basicPay, double sssContribution,
                                  double withholdingTax, double netPay) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_POSITION, position);
        contentValues.put(COLUMN_CIVIL_STATUS, civilStatus);
        contentValues.put(COLUMN_DAYS_WORKED, daysWorked);
        contentValues.put(COLUMN_BASIC_PAY, basicPay);
        contentValues.put(COLUMN_SSS_CONTRIBUTION, sssContribution);
        contentValues.put(COLUMN_WITHHOLDING_TAX, withholdingTax);
        contentValues.put(COLUMN_NET_PAY, netPay);

        // Update the row and return success
        int result = db.update(TABLE_EMPLOYEE, contentValues, COLUMN_EMPLOYEE_ID + " = ?", new String[]{employeeId});
        return result > 0; // Returns true if the row was updated, false otherwise
    }

    // Method to check if an employee ID already exists
    public boolean employeeIdExists(String employeeId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_EMPLOYEE + " WHERE " + COLUMN_EMPLOYEE_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{employeeId});
        boolean exists = (cursor.getCount() > 0);
        cursor.close();
        return exists;
    }
}

