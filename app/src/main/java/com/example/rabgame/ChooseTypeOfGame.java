package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class ChooseTypeOfGame extends AppCompatActivity {
private ImageView jump,evade,limbo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_type_of_game);
        jump = findViewById(R.id.jump);
        evade = findViewById(R.id.evade);
        limbo = findViewById(R.id.limbo);
        jump.setOnClickListener(CreatesetOnclickListener());
        evade.setOnClickListener(CreatesetOnclickListener());
        limbo.setOnClickListener(CreatesetOnclickListener());
    }
    public View.OnClickListener CreatesetOnclickListener()
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.jump:
                        CreateEvadeIntent(); break;
                    case R.id.evade:
                        CreateShopIntent(); break;
                    case R.id.limbo:
                        CreateExitIntent();break;
                }
            }
        };
        return onClickListener;
    }
    void CreateEvadeIntent()
    {
        Intent intent = new Intent(ChooseTypeOfGame.this,EvadingGame.class);
        startActivity(intent);
    }
    void CreateExitIntent()
    {
        this.finish();
    }
    void CreateShopIntent()
    {
        Intent intent = new Intent(ChooseTypeOfGame.this,Shop.class);
        startActivity(intent);
    }
}
