package com.example.personal_wuzhen.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MyDBHelper extends SQLiteOpenHelper {
    private static final String DBName="UserInfo";
    private static final int DBVersion=1;
    //构造方法 参数分别为上下文环境 数据库名 查询的结果集 数据库版本号
    public MyDBHelper(@Nullable Context context) {
        super(context, DBName, null, DBVersion);
    }

    @Override
    //创建数据库的方法
    public void onCreate(SQLiteDatabase db) {
        //创建数据表
        db.execSQL("create table tb_UserInfo(id integer primary key autoincrement,UserName varchar(10),UserPw varchar(15),UserSex varchar(10))");
    }
    //升级数据库
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
