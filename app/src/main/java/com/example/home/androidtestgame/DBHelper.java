package com.example.home.androidtestgame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Home on 10.3.2018 г..
 */

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "TestV2.db";
    public static final int DATABASE_VERSION = 1;


    public static final String User = "User";
    public static final String UserType = "UserType";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE " + User + " (_id INTEGER PRIMARY KEY AUTOINCREMENT,FacultyNumber TEXT,Name TEXT,Gender TEXT,Password TEXT, UserTypeID INTEGER,FOREIGN KEY(UserTypeID) REFERENCES UserType(_id))");
        db.execSQL("CREATE TABLE " + UserType + "(_id INTEGER PRIMARY KEY,Name TEXT)");
        db.execSQL("INSERT INTO " + UserType + "(_id,Name) VALUES(1,'Студент')");
    }

    public boolean addStudent(String FNumber,String fullName,String password,String userType,String gender) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("FacultyNumber", FNumber);
        cv.put("Name", fullName);
        cv.put("Gender", gender);
        cv.put("Password",password);
        cv.put("UserTypeID", userType);
        db.insert("User", null, cv);
        db.close();
        return true;
    }

    public ArrayList<String> getAllUserTypes(){
        ArrayList<String> userTypes = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selectQuery = "SELECT * FROM " + UserType;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if(cursor.getCount()>0){
            while(cursor.moveToNext()){
                String type = cursor.getString(cursor.getColumnIndex("_id"));
                userTypes.add(type);
            }
        }
        return userTypes;
    }
    public boolean LogInStudent(String password,String fNumber){
        SQLiteDatabase db = getReadableDatabase();
       String retrieveQuery = "SELECT * FROM " + User + " WHERE Password= ? AND FacultyNumber= ? ";

        Cursor cursor = db.rawQuery(retrieveQuery,new String[]{password,fNumber});
        if(cursor.moveToFirst()){
               password = cursor.getString(cursor.getColumnIndex("Password"));
               fNumber = cursor.getString(cursor.getColumnIndex("FacultyNumber"));
        }
        return true;
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

     db.execSQL("DROP TABLE IF EXISTS User");
     db.execSQL("DROP TABLE IF EXISTS UserType");

     onCreate(db);
    }

}
