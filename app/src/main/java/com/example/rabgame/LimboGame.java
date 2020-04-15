package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class LimboGame extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView Qcoins, status;
    private Boolean Jump = false;
    private Button button;
    ImageView crab,back;
    private Boolean GameOver = false;
    private int mintarget = 10, maxtarget = 35;
    private Boolean Up = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_limbo_game);

        back = findViewById(R.id.back5);
        back.setOnClickListener(CreatesetOnclickListener());
        crab = findViewById(R.id.crab2);
        chooseskin();
        button = (Button) findViewById(R.id.limb);
        button.setOnClickListener(CreatesetOnclickListener());
        progressBar = findViewById(R.id.progresslimbBar3);
        Qcoins = findViewById(R.id.qcoins3);
        Qcoins.setText("0");
        status = findViewById(R.id.gameStatus3);


        Thread myThready = new Thread(new Runnable() {
            public void run() {
                while (!GameOver) {

                    try {
                        sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (!GameOver) {
                                SetprogressBar(progressBar);
                                drawProgreesBar(mintarget, maxtarget, progressBar);
                            }
                        }
                    });
                }
            }
        });
        myThready.start();
    }

    public View.OnClickListener CreatesetOnclickListener() {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.limb:
                        if (!GameOver) {
                            Jump();
                            break;
                        } else {

                            Restart();
                        }break;
                    case R.id.back5:
                        BackIntent();break;


                }
            }
        };
        return onClickListener;
    }

    void Restart() {
        CustomizedUser.coins += Integer. parseInt(Qcoins.getText().toString());
        this.recreate();
    }

    void Jump() {
        if (progressBar.getProgress() < maxtarget && progressBar.getProgress() > mintarget) {
            Integer coins = Integer.parseInt(Qcoins.getText().toString()) + 2;
            Qcoins.setText(coins.toString());
        } else {
            button.setText("Restart");
            GameOver = true;
            status.setText("Game over");
            status.setVisibility((int) 1);
        }
        Jump = true;
    }

    void SetprogressBar(ProgressBar progressBar) {
        if (progressBar.getProgress() == progressBar.getMax()) {
            Up = false;
        }
        if (progressBar.getProgress() == 0) {
            Up = true;
        }
        if (Up) {
            progressBar.setProgress(progressBar.getProgress() + 1);
        } else {
            progressBar.setProgress(progressBar.getProgress() - 1);
        }
    }

    void drawProgreesBar(int mintarget, int maxtarget, ProgressBar progressBar) {
        if (progressBar.getProgress() > mintarget && progressBar.getProgress() < maxtarget) {
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.verticalprogressbar2));
        } else {

            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.verticalprogressbar));
        }
    }
    void chooseskin()
    {
        switch (CustomizedUser.skin)
        {
            case "crab_1":
                crab.setImageResource(R.drawable.crab_1);break;
            case "crab_2":
                crab.setImageResource(R.drawable.crab_2);break;
            case "crab_3":
                crab.setImageResource(R.drawable.crab_3);break;
            case "crab_4":
                crab.setImageResource(R.drawable.crab_4);break;
            case "crab_5":
                crab.setImageResource(R.drawable.crab_5);break;

        }


    }
    void BackIntent()
    {
        Intent intent = new Intent(LimboGame.this,ChooseTypeOfGame.class);
        startActivity(intent);
        this.finish();
    }
}
