package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button start,shop,rule,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button)findViewById(R.id.start);
        shop = (Button)findViewById(R.id.shop);
        rule = (Button)findViewById(R.id.rule);
        exit = (Button)findViewById(R.id.exit);
        start.setOnClickListener(CreatesetOnclickListener());
        shop.setOnClickListener(CreatesetOnclickListener());
        rule.setOnClickListener(CreatesetOnclickListener());
        exit.setOnClickListener(CreatesetOnclickListener());

        final SQLiteDatabase db = new DBHelper(this).getWritableDatabase();
        Cursor cursor = db.query("GameUser", null, null, null,
                null, null, null);
        if (cursor != null && cursor.moveToFirst()) {
            cursor.moveToFirst();
            CustUser.skin = cursor.getString(cursor.getColumnIndex("activeskin"));
            CustUser.coins = cursor.getInt(cursor.getColumnIndex("coins"));
        }

    }

    public View.OnClickListener CreatesetOnclickListener()
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.start:
                        CreateStartIntent(); break;
                    case R.id.shop:
                        CreateShopIntent(); break;
                    case R.id.exit:
                        CreateExitIntent();break;
                }
            }
        };
        return onClickListener;
    }


    void CreateStartIntent()
    {
        Intent intent = new Intent(MainActivity.this,ChooseTypeOfGame.class);
        startActivity(intent);
    }
    void CreateExitIntent()
    {
        this.finish();
    }
    void CreateShopIntent()
    {
        Intent intent = new Intent(MainActivity.this,Shop.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ContentValues cv = new ContentValues();
        cv.put("coins",CustUser.coins);
        cv.put("activeskin",CustUser.skin);
        final SQLiteDatabase db2 = new DBHelper(this).getWritableDatabase();
        int cursor = db2.update("GameUser", cv, null,
                null);
        db2.close();
    }
}
