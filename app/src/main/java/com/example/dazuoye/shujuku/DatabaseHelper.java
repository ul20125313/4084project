package com.example.dazuoye.shujuku;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {

    String TAG="shujuku";
    public DatabaseHelper(@Nullable Context context) {
        super(context, shujukuinfo.name, null, shujukuinfo.version_code);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: database update successfully");
        String sql="create table user(zh varchar,password varchar,dh varchar,dz varchar,bj varchar,ye varchar,xm varchar,dingdanid varchar,im varchar)";
        db.execSQL(sql);
        sql="create table dingdans(id varchar,nr varchar,zh varchar)";
        db.execSQL(sql);
        sql="create table zhfp(id varchar)";
        db.execSQL(sql);
        ContentValues tvalues=new ContentValues();
        tvalues.put("id","18110543005");
        db.insert("zhfp",null,tvalues);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
