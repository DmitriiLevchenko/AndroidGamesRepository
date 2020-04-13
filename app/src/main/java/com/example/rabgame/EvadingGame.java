package com.example.rabgame;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import EvadingLogik.GameViewEvading;

public class EvadingGame  extends AppCompatActivity implements View.OnTouchListener {
    static public boolean isLeftPressed = false;
    static public  boolean isRightPressed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evading_game);
        GameViewEvading gameView = new GameViewEvading(this); // создаём gameView

        LinearLayout gameLayout = (LinearLayout) findViewById(R.id.gameLayout); // находим gameLayout
        gameLayout.addView(gameView); // и добавляем в него gameView

        ImageView leftButton = (ImageView) findViewById(R.id.left); // находим кнопки
        ImageView rightButton = (ImageView) findViewById(R.id.right);

        leftButton.setOnTouchListener(this); // и добавляем этот класс как слушателя (при нажатии сработает onTouch)
        rightButton.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View button, MotionEvent motion) {
        switch(button.getId()) { // определяем какая кнопка
            case R.id.left:
                switch (motion.getAction()) { // определяем нажата или отпущена
                    case MotionEvent.ACTION_DOWN:
                        isLeftPressed = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        isLeftPressed = false;
                        break;
                }
                break;
            case R.id.right:
                switch (motion.getAction()) { // определяем нажата или отпущена
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
