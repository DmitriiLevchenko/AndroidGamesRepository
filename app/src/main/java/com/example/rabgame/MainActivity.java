package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    public static String LOGNAME = "GameLog";
    private Button start, shop, rule, exit;
    private final String TABLENAME = "GameUser";
    private  DBHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.start);
        shop = (Button) findViewById(R.id.shop);
        rule = (Button) findViewById(R.id.rule);
        exit = (Button) findViewById(R.id.exit);
        start.setOnClickListener(CreatesetOnclickListener());
        shop.setOnClickListener(CreatesetOnclickListener());
        rule.setOnClickListener(CreatesetOnclickListener());
        exit.setOnClickListener(CreatesetOnclickListener());
        dbHelper = new DBHelper(this);
        getDatefromDB();


    }

    public View.OnClickListener CreatesetOnclickListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.start:
                        CreateStartIntent();
                        break;
                    case R.id.shop:
                        CreateShopIntent();
                        break;
                    case R.id.exit:
                        CreateExitIntent();
                        break;
                    case R.id.rule:
                        CreateRuleIntent();
                        break;
                }
            }
        };
        return onClickListener;
    }

    public void getDatefromDB()
    {
        if(CustomizedUser.skin == "nule" && CustomizedUser.coins == -1)
        {
            final SQLiteDatabase db = dbHelper.getWritableDatabase();
            Cursor cursor = db.query(TABLENAME, null, null, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                cursor.moveToFirst();
                CustomizedUser.skin = cursor.getString(cursor.getColumnIndex("activeskin"));
                CustomizedUser.coins = cursor.getInt(cursor.getColumnIndex("coins"));
                Log.d(MainActivity.LOGNAME, String.valueOf(cursor.getString(cursor.getColumnIndex("activeskin"))+cursor.getInt(cursor.getColumnIndex("coins"))));
            }
            else
            {
                Log.d(MainActivity.LOGNAME, String.valueOf("Create db"));
                ContentValues cv = new ContentValues();
                cv.put("coins", 0);
                cv.put("activeskin", "crab_1");
                long rowID = db.insert("mytable", null, cv);
                getDatefromDB();
            }
        }
    }
    void CreateStartIntent() {
        Intent intent = new Intent(MainActivity.this, ChooseTypeOfGame.class);
        startActivity(intent);
        this.finish();

    }

    void CreateExitIntent() {
        this.finish();
    }

    void CreateShopIntent() {
        Intent intent = new Intent(MainActivity.this, Shop.class);
        startActivity(intent);
        this.finish();

    }
    void CreateRuleIntent() {
        Intent intent = new Intent(MainActivity.this, Rules.class);
        startActivity(intent);
        this.finish();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ContentValues cv = new ContentValues();
        cv.put("coins", CustomizedUser.coins);
        cv.put("activeskin", CustomizedUser.skin);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int cursor = db.update(TABLENAME, cv, null,
                null);

    }
}
