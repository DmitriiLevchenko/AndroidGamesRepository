package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
}
