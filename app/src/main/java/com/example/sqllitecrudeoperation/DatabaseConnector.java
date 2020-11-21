package com.example.sqllitecrudeoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;

import androidx.annotation.Nullable;

public class DatabaseConnector extends SQLiteOpenHelper {
    public DatabaseConnector(@Nullable Context context) {
        super(context, Contants.DB_NAME,null,Contants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contants.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL( "DROP TABLE IF EXISTS "  + Contants.TABLE_NAME);
        onCreate(db);
    }
// insert data
    public long insertStudentInfo(String name,String regNumber, String pcSerialNumber,String image, String phoneNumber,String AddTime,String updateTime){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Contants.S_FULL_NAMES,name);
        values.put(Contants.S_REGISTRATION_NUMBER,regNumber);
        values.put(Contants.S_PC_SERIAL_NUMBER,pcSerialNumber);
        values.put(Contants.S_IMAGE,image);
        values.put(Contants.S_PHONE_NUMBER,phoneNumber);
        values.put(Contants.S_ADD_TIMESTAMP,AddTime);
        values.put(Contants.S_UPDATE_TIMESTAMP,updateTime);
        long id=db.insert(Contants.TABLE_NAME,null,values);
        db.close();
        return id;

    }

}
