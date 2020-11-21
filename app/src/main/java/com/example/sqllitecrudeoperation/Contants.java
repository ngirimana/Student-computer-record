package com.example.sqllitecrudeoperation;

public class Contants {
    //database name
    public static final String DB_NAME= "STUDENT_INFO";

    //version
    public static final int DB_VERSION = 1;

    //table name
    public static final String TABLE_NAME= "STUDENTS_INFO";
    //table column
    public static final String S_ID="STUD_ID";
    public static final String S_FULL_NAMES="STUD_NAMES";
    public static final String S_REGISTRATION_NUMBER = "STUD_REG_NUMBER";
    public static final String S_PC_SERIAL_NUMBER = "STUD_PC_SERIAL_NUMBER";
    public static final String S_IMAGE = "STUD_IMAGE";
    public static final String S_PHONE_NUMBER = "STUD_PHONE_NUMBER";
    public static final String S_ADD_TIMESTAMP = "STUD_ADD_TIMESTAMP";
    public static final String S_UPDATE_TIMESTAMP = "STUD_UPDATE_TIMESTAMP";

    // CREATE  query for creating table
    public static final String CREATE_TABLE="CREATE TABLE " + TABLE_NAME + "("
            + S_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + S_FULL_NAMES + " TEXT,"
            + S_REGISTRATION_NUMBER + " TEXT,"
            + S_PC_SERIAL_NUMBER + " TEXT,"
            + S_IMAGE + " TEXT,"
            + S_PHONE_NUMBER + " TEXT,"
            + S_ADD_TIMESTAMP +  " TEXT,"
            + S_UPDATE_TIMESTAMP +  " TEXT"
            + ")";
    }
