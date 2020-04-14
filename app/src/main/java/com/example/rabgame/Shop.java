package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Shop extends AppCompatActivity {
    private SharedPreferences.Editor ed;
    private ImageView crabprice1, crabprice2, crabprice3, crabprice4;
    private TextView textView;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        crabprice1 = findViewById(R.id.shopprices_1);
        back = findViewById(R.id.back);
        crabprice2 = findViewById(R.id.shopprices_2);
        crabprice3 = findViewById(R.id.shopprices_3);
        crabprice4 = findViewById(R.id.shopprices_4);
        crabprice1.setOnClickListener(CreatesetOnclickListener());
        crabprice2.setOnClickListener(CreatesetOnclickListener());
        crabprice3.setOnClickListener(CreatesetOnclickListener());
        crabprice4.setOnClickListener(CreatesetOnclickListener());
        textView = findViewById(R.id.textView2);
        textView.setText(CustomizedUser.coins + "");
        View.OnClickListener OnbackListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateIntent();
            }
        };
        back.setOnClickListener(OnbackListener);
    }

    public View.OnClickListener CreatesetOnclickListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.shopprices_1:
                        buyskin(100, "crab_2");
                        break;
                    case R.id.shopprices_2:
                        buyskin(250, "crab_3");
                        break;
                    case R.id.shopprices_3:
                        buyskin(500, "crab_4");
                        break;
                    case R.id.shopprices_4:
                        buyskin(1000, "crab_5");
                        break;
                }
            }
        };
        return onClickListener;
    }

    public void CreateIntent() {
        Intent intent = new Intent(Shop.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }

    public void buyskin(int cost, String name) {
        if (CustomizedUser.coins >= cost) {
            CustomizedUser.coins -= cost;
            CustomizedUser.skin = name;
            textView.setText(CustomizedUser.coins + "");
        }
    }

}
