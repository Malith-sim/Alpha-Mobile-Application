package com.example.registrationapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME ="Registration.db";
    public static final String TABLE_NAME ="Registration_Table";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="Name";
    public static final String COL_3 ="Gender";
    public static final String COL_4 ="Email";
    public static final String COL_5 ="Phone";
    public static final String COL_6 ="Password";

    static String loginuserID;
    static String loginuserName;
    static String loginuserGender;
    static String loginuserEmail;
    static String loginuserPhone;
    static String loginuserPassword;

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, 1);
    }
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " +TABLE_NAME+ "(ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT,GENDER TEXT, EMAIL TEXT, PHONE INTEGER, PASSWORD TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String name, String gender, String email, int phone, String pw){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues val = new ContentValues();
        val.put(COL_2, name);
        val.put(COL_3, gender);
        val.put(COL_4, email);
        val.put(COL_5, phone);
        val.put(COL_6, pw);
        sqLiteDatabase.insert(TABLE_NAME, null, val);
    }

    public Cursor ViewLoginData(String email,String pw){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("Select * FROM "+TABLE_NAME +" Where Email='"+email+"' and Password='"+pw+"'", new String[]{});
        return cursor;

    }

    public void updatedata(String teleno,SQLiteDatabase database){

        String updateque ="UPDATE  Registration_Table SET Phone='"+teleno+"' WHERE ID='"+ DatabaseHelper.loginuserID +"'";

        database.execSQL(updateque);

    }
    public void deleteData(SQLiteDatabase database){

        String updateque ="DELETE FROM  Registration_Table WHERE ID='"+ DatabaseHelper.loginuserID +"'";

        database.execSQL(updateque);

    }

}
