package com.example.android.bloodbank;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.http.SslCertificate;

public class MyDBHandler extends SQLiteOpenHelper {
    //information of database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "BloodBank.db";
    public static final String TABLE_NAME = "Donor";
    public static final String COLUMN_NAME = "Donor_Name";
    public static final String COLUMN_ID = "Donor_ID";
    public static final String COLUMN_GENDER = "Donor_Gender";
    public static final String COLUMN_AGE = "Donor_Age";
    public static final String COLUMN_DATE = "Donor_Date";
    public static final String COLUMN_HOSPITAL = "Donor_Hospital";
    public static final String COLUMN_CITY = "Donor_City";

    //initialize the database
    public MyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_NAME +
                "TEXT," + COLUMN_ID + "TEXT PRIMARY KEY," + COLUMN_GENDER + "TEXT,"
                + COLUMN_AGE + "TEXT," + COLUMN_DATE + "TEXT,"
                + COLUMN_HOSPITAL + "TEXT," + COLUMN_CITY + "TEXT )";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public String loadHandler() {
        String result = "";
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            String result_0 = cursor.getString(0);
            String result_1 = cursor.getString(1);
            String result_2 = cursor.getString(2);
            String result_3 = cursor.getString(3);
            String result_4 = cursor.getString(4);
            String result_5 = cursor.getString(5);
            String result_6 = cursor.getString(6);

            result += result_0 + " " + result_1 + " " + result_2 + " " + result_3 + " " +
                    result_4 + " " + result_5 + " " + result_6 + System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }

    public void addHandler(Donor donor) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, donor.getDName());
        values.put(COLUMN_ID, donor.getDID());
        values.put(COLUMN_GENDER, donor.getDGender());
        values.put(COLUMN_AGE, donor.getDAge());
        values.put(COLUMN_DATE, donor.getDDate());
        values.put(COLUMN_HOSPITAL, donor.getDHospital());
        values.put(COLUMN_CITY, donor.getDCity());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public Donor searchHandler(String donorID) {
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME + " = " + "'" + donorID + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Donor donor = new Donor();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            donor.setDName(cursor.getString(0));
            donor.setDID(cursor.getString(1));
            donor.setDGender(cursor.getString(2));
            donor.setDAge(cursor.getString(3));
            donor.setDDate(cursor.getString(4));
            donor.setDHospital(cursor.getString(5));
            donor.setDCity(cursor.getString(6));
            cursor.close();
        } else {
            donor = null;
        }
        db.close();
        return donor;
    }


    public boolean deleteHandler(String ID) {

        boolean result = false;
        String query = "Select * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "= '" + String.valueOf(ID) + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Donor donor = new Donor();
        if (cursor.moveToFirst()) {
            donor.setDID(cursor.getString(1));
            db.delete(TABLE_NAME, COLUMN_ID + "=?",
                    new String[]{
                            String.valueOf(donor.getDID())
                    });
            cursor.close();
            result = true;
        }
        db.close();
        return result;

    }

    public boolean updateHandler(String Name, String ID, String Gender, String Age, String Date, String Hospital, String City) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(COLUMN_NAME, Name);
        args.put(COLUMN_ID, ID);
        args.put(COLUMN_GENDER, Gender);
        args.put(COLUMN_AGE, Age);
        args.put(COLUMN_DATE, Date);
        args.put(COLUMN_HOSPITAL, Hospital);
        args.put(COLUMN_CITY, City);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;
    }
}


