package com.example.rabgame;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, "GameUser.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table GameUser ("
                + "id integer primary key autoincrement,"
                + "coins integer,"
                + "activeskin text" + ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

