package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ChooseTypeOfGame extends AppCompatActivity {
    private ImageView jump, evade, limbo;
    //private String gamename = "",coinsQ = "";
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type_of_game);

//        Intent intent = getIntent();
////
////         gamename = intent.getStringExtra("game");
////         coinsQ = intent.getStringExtra("coins");
////        if(!gamename.isEmpty() && !coinsQ.isEmpty())
////        {
////            TextView textView = findViewById(R.id.textView);
////            textView.setText("Nice game in " + gamename + "you earned" + coinsQ);
////        }

        back = findViewById(R.id.back2);
        back.setOnClickListener(CreatesetOnclickListener());
        jump = findViewById(R.id.jump);
        evade = findViewById(R.id.evade);
        limbo = findViewById(R.id.limbo);
        jump.setOnClickListener(CreatesetOnclickListener());
        evade.setOnClickListener(CreatesetOnclickListener());
        limbo.setOnClickListener(CreatesetOnclickListener());
    }

    public View.OnClickListener CreatesetOnclickListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.jump:
                        CreateJumpIntent();
                        break;
                    case R.id.evade:
                        CreateEvadeIntent();
                        break;
                    case R.id.limbo:
                        CreateLimboIntent();
                        break;
                    case R.id.back2:
                        CreatebackIntent();
                        break;
                }
            }
        };
        return onClickListener;
    }


    void CreateEvadeIntent() {
        Intent intent = new Intent(ChooseTypeOfGame.this, EvadingGame.class);
        startActivity(intent);
        this.finish();

    }

    void CreateJumpIntent() {
        Intent intent = new Intent(ChooseTypeOfGame.this, JumpGame.class);
        startActivity(intent);
        this.finish();

    }

    void CreateLimboIntent() {
        Intent intent = new Intent(ChooseTypeOfGame.this, LimboGame.class);
        startActivity(intent);
        this.finish();

    }

    void CreatebackIntent() {
        Intent intent = new Intent(ChooseTypeOfGame.this, MainActivity.class);
        startActivity(intent);
        this.finish();

    }
}
