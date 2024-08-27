package com.example.Chat.dataoperation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context) {
        super(context, "orange.db3", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建用户表
        String sql = "create table orange_user(id integer primary key autoincrement, username varchar(50), password varchar(50),sex varchar(10),city carchar(50))";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 插入数据
     *
     * @param sqLiteDatabase
     * @param username
     * @param password
     * @param sex
     * @param city
     */
    public void insertUser(SQLiteDatabase sqLiteDatabase, String username, String password, String sex, String city) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);
        contentValues.put("sex", sex);
        contentValues.put("city", city);
        sqLiteDatabase.insert("orange_user", null, contentValues);
        sqLiteDatabase.close();
    }

    /**
     * 查询数据
     *
     * @param sqLiteDatabase
     * @param bundle
     * @return
     */
    public Bundle queryUserInfo(SQLiteDatabase sqLiteDatabase, Bundle bundle) {
        String username = bundle.getString("username");
        Cursor cursor = sqLiteDatabase.rawQuery("select * from orange_user where username=?", new String[]{username});
        if (cursor != null) {
            while (cursor.moveToNext()) {
                bundle.putString("sex", cursor.getString(3));
                bundle.putString("city", cursor.getString(4));
            }
        }
        cursor.close();
        sqLiteDatabase.close();
        return bundle;
    }
}
