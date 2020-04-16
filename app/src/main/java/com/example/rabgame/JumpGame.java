package com.example.rabgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static java.lang.Thread.sleep;

public class JumpGame extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView Qcoins,status;
    private Boolean Jump = false;
    private Button button;
    private Boolean GameOver = false;
    private int target = 50,mintarget = 35,maxtarget = 65;
    private Boolean Up = true;
    ImageView crab,back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jump_game);

        crab = findViewById(R.id.crab);
        chooseskin();
        back = findViewById(R.id.back4);
        back.setOnClickListener(CreatesetOnclickListener());
        button = (Button)findViewById(R.id.jumpButton);
        button.setOnClickListener(CreatesetOnclickListener());
        progressBar = findViewById(R.id.progressjumpBar);
        Qcoins = findViewById(R.id.Qcoins);
        Qcoins.setText("0");
        status = findViewById(R.id.gameStatus);

        Thread myThready = new Thread(new Runnable()
        {
            public void run()
            {
                while(!GameOver)
                {
                    while(Jump == false)
                    {
                        try {
                            sleep(8);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(!GameOver)
                                {
                                    SetprogressBar(progressBar);
                                    drawProgreesBar(mintarget,maxtarget,progressBar);
                                }
                            }
                        });

                    }
                     target = 15 + (int) (Math.random() * (progressBar.getMax()-15));
                     mintarget = target-15;
                     maxtarget = target+15;
                     Jump = false;
                }

            }
        });
        myThready.start();
    }
    public View.OnClickListener CreatesetOnclickListener()
    {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.jumpButton:
                        if(!GameOver)
                        {
                            Jump(); break;
                        }
                        else
                        {

                            Restart();
                        }break;
                    case R.id.back4:
                        BackIntent();break;

                }
            }
        };
        return onClickListener;
    }
    void Restart()
    {
        CustomizedUser.coins += Integer. parseInt(Qcoins.getText().toString());
        Log.d(MainActivity.LOGNAME, String.valueOf(CustomizedUser.coins));
        this.recreate();
    }
    void Jump()
    {
        if(progressBar.getProgress() < maxtarget && progressBar.getProgress() > mintarget)
        {
            Integer coins = Integer. parseInt(Qcoins.getText().toString())+2;
            Qcoins.setText(coins.toString());
            Jump = true;
        }
        else
        {
            button.setText("Restart");
            Jump = true;
            GameOver = true;
            status.setText("Game over");
            status.setVisibility((int)0.9);
        }
    }
    void SetprogressBar(ProgressBar progressBar)
    {
        if(progressBar.getProgress() == progressBar.getMax())
        {
            Up = false;
        }
        if(progressBar.getProgress() == 0)
        {
            Up = true;
        }
        if(Up)
        {
            progressBar.setProgress(progressBar.getProgress()+1);
        }
        else
        {
            progressBar.setProgress(progressBar.getProgress()-1);
        }
    }
    void  drawProgreesBar(int mintarget,int maxtarget,ProgressBar progressBar)
    {
        if(progressBar.getProgress()>mintarget && progressBar.getProgress() < maxtarget)
        {
            progressBar.setProgressDrawable(getResources().getDrawable(R.drawable.verticalprogressbar2));
        }
        else {

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
        this.finish();
    }
}
