package com.example.medicinedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DbConnection extends SQLiteOpenHelper {
    public DbConnection(Context context) {
        super(context, "MedicineDBase", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase dbase) {
        dbase.execSQL("create Table MedTable(medicineName TEXT primary key, date TEXT, time TEXT)");//Creates the table
    }

    @Override
    public void onUpgrade(SQLiteDatabase dbase, int i, int i1) {
    }

    public boolean insertValues(String medName, String medDate, String medTime){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("medicineName",medName);
        contentValues.put("date",medDate);
        contentValues.put("time",medTime);
        long result = database.insert("MedTable",null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }

    public Cursor RetrieveData(String date, String time){
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery("Select * from MedTable where date= '"+date+"' AND time='"+time+"'", null);
        return cursor;
    }
}
