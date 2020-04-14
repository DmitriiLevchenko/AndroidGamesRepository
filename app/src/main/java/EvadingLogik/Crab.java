package EvadingLogik;

import android.content.Context;

import com.example.rabgame.EvadingGame;
import com.example.rabgame.MainActivity;
import com.example.rabgame.R;

public class Crab extends JungleBody {
    public Crab(Context context) {
        bitmapId = R.drawable.crab_1; // определяем начальные параметры
        size = 4;
        x=5;
        y= GameViewEvading.maxY - size - 1;
        speed = (float) 1.2;

        init(context); // инициализируем корабль
    }
    @Override
    public void update() { // перемещаем корабль в зависимости от нажатой кнопки
        if(EvadingGame.isLeftPressed && x >= 0){
            x -= speed;
        }
        if(EvadingGame.isRightPressed && x <= GameViewEvading.maxX - 5){
            x += speed;
        }
    }
}
