package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Rules extends AppCompatActivity {
private ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);
        back = findViewById(R.id.back3);
        back.setOnClickListener(CreatesetOnclickListener());



    }
    public View.OnClickListener CreatesetOnclickListener()
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.back3:
                        CreateIntent();break;
                }
            }
        };
        return onClickListener;
    }
    public void CreateIntent() {
        Intent intent = new Intent(Rules.this, MainActivity.class);
        startActivity(intent);
        this.finish();
    }
}
