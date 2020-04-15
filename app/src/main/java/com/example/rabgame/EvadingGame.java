package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import EvadingLogik.GameViewEvading;

public class EvadingGame extends AppCompatActivity implements View.OnTouchListener {
    static public boolean isLeftPressed = false;
    static public boolean isRightPressed = false;
    public TextView coinsQn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evading_game);

        android.os.Handler h = new android.os.Handler() {
            public void handleMessage(android.os.Message msg) {
                coinsQn = findViewById(R.id.count);
                int count = Integer.parseInt(coinsQn.getText().toString())+1;
                coinsQn.setText(count+"");
            };
        };


        GameViewEvading gameView = new GameViewEvading(this);

        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout);
        gameLayout.addView(gameView); // и добавляем в него gameView

        ImageView leftButton = (ImageView) findViewById(R.id.left);
        ImageView rightButton = (ImageView) findViewById(R.id.right);
        leftButton.setOnTouchListener(this);
        rightButton.setOnTouchListener(this);
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


}
