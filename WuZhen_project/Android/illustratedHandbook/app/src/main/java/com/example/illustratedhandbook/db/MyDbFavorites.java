package com.example.illustratedhandbook.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.illustratedhandbook.JsonToBean;

public class MyDbFavorites extends SQLiteOpenHelper {
    private static final String DBName="UserFavorites";
    private static final int DBVersion=1;


    public MyDbFavorites(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table tb_UserFavorites(id integer primary key autoincrement,userName varchar(20)," +
                "category varchar(10),petId tinyint,petName varchar(15),petEngName varchar(30),petPrice varchar(20),petUrl varchar(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
