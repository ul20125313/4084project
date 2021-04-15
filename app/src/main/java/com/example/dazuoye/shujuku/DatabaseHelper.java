package com.example.dazuoye.shujuku;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {// android studio local database

    String TAG="shujuku";
    public DatabaseHelper(@Nullable Context context) {
        super(context, shujukuinfo.name, null, shujukuinfo.version_code);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: database update successfully");
        String sql="create table user(zh varchar,password varchar,dh varchar,dz varchar,bj varchar,ye varchar,xm varchar,dingdanid varchar,im varchar)";// the sql statement, it is used to create an account table that contains user's personal details
        db.execSQL(sql);
        sql="create table dingdans(id varchar,nr varchar,zh varchar)";// create the singal order table
        db.execSQL(sql);
        sql="create table zhfp(id varchar)";//create the account distribution table
        db.execSQL(sql);
        ContentValues tvalues=new ContentValues();
        tvalues.put("id","18110543005");//the first original account number, the account number behind it will plus it by one
        db.insert("zhfp",null,tvalues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
