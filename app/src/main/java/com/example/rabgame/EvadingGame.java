package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import EvadingLogik.GameViewEvading;

public class EvadingGame extends AppCompatActivity implements View.OnTouchListener,IGameViewEvading {
    static public boolean isLeftPressed = false;
    static public boolean isRightPressed = false;
    public ImageView back;


    @Override
    public void checkCollision() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evading_game);

        GameViewEvading gameView = new GameViewEvading(this,this);
        LinearLayout gameLayout = findViewById(R.id.gameLayout);
        gameLayout.addView(gameView); // и добавляем в него gameView
        back = findViewById(R.id.backevad);
        back.setOnClickListener(CreatesetOnclickListener());
        ImageView leftButton = (ImageView) findViewById(R.id.left);
        ImageView rightButton = (ImageView) findViewById(R.id.right);

        leftButton.setOnTouchListener(this);
        rightButton.setOnTouchListener(this);
    }
    public View.OnClickListener CreatesetOnclickListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.backevad:
                        CreateIntentBack();
                        break;
                }
            }
        };
        return onClickListener;
    }
    @Override
    public boolean onTouch(View button, MotionEvent motion) {
        switch (button.getId()) {
            case R.id.left:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }
                break;
            case R.id.right:
                switch (motion.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        isRightPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isRightPressed = false;
                        break;
                }
                break;
        }
        return true;
    }
    public void CreateIntentBack()
    {
        this.finish();
    }

}
