package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Shop extends AppCompatActivity {
    private SharedPreferences.Editor ed;
    private ImageView  crabprice1,crabprice2,crabprice3,crabprice4;
    private  TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        crabprice1 = findViewById(R.id.shopprices_1);
        crabprice2 = findViewById(R.id.shopprices_2);
        crabprice3 = findViewById(R.id.shopprices_3);
        crabprice4 = findViewById(R.id.shopprices_4);
        crabprice1.setOnClickListener(CreatesetOnclickListener());
        crabprice2.setOnClickListener(CreatesetOnclickListener());
        crabprice3.setOnClickListener(CreatesetOnclickListener());
        crabprice4.setOnClickListener(CreatesetOnclickListener());
       textView =  findViewById(R.id.textView2);
        textView.setText(CustUser.coins + "");

    }
    public View.OnClickListener CreatesetOnclickListener()
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.shopprices_1:
                        if(CustUser.coins >= 100)
                        {
                            CustUser.coins-=100;
                            CustUser.skin = "crab_2";
                            textView.setText(CustUser.coins + "");
                        }break;
                    case R.id.shopprices_2:
                        if(CustUser.coins >= 250)
                        {
                            CustUser.coins-=250;
                            CustUser.skin = "crab_3";
                            textView.setText(CustUser.coins + "");
                        }break;
                    case R.id.shopprices_3:
                        if(CustUser.coins >= 500)
                        {
                            CustUser.coins-=500;
                            CustUser.skin = "crab_4";
                            textView.setText(CustUser.coins + "");
                        }break;
                    case R.id.shopprices_4:
                        if(CustUser.coins >= 1000)
                        {
                            CustUser.coins-=1000;
                            CustUser.skin = "crab_5";
                            textView.setText(CustUser.coins + "");
                        }break;
                }
            }
        };
        return onClickListener;
    }

}
