package EvadingLogik;

import android.content.Context;
import android.util.Log;

import com.example.rabgame.MainActivity;
import com.example.rabgame.R;

import java.util.Random;

public class Сoconut extends JungleBody {
    private int radius = 2; // радиус
    private float minSpeed = (float) 1.4; // минимальная скорость
    private float maxSpeed = (float) 1.4; // максимальная скорость
    public Сoconut(Context context) {
        Random random = new Random();

        bitmapId = R.drawable.coconut;
        y=0;
        x = random.nextInt(GameViewEvading.maxX) - radius;
        size = radius*2;
        speed = minSpeed + (maxSpeed - minSpeed) * random.nextFloat();

        init(context);
    }
    @Override
    public void update() {
        y += speed;
    }
    public boolean isCollision(float shipX, float shipY, float shipSize) {
        return !(((x+size) < shipX)||(x > (shipX+shipSize))||((y+size) < shipY)||(y > (shipY+shipSize)));
    }
    public boolean isEnd(int height,float blocksize) {
        //Log.d(MainActivity.LOGNAME, String.valueOf(this.y*blocksize+size/2*blocksize) + "<" + height);
        if(this.y*blocksize+size/2*blocksize+15 >= height)return true;
        else return  false;
    }
}
