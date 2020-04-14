package EvadingLogik;

import android.content.Context;

import com.example.rabgame.CustomizedUser;
import com.example.rabgame.EvadingGame;
import com.example.rabgame.R;

public class Crab extends JungleBody {
    public Crab(Context context) {
        switch (CustomizedUser.skin)
        {
            case "crab_1":
                bitmapId = (R.drawable.crab_1);break;
            case "crab_2":
                bitmapId = (R.drawable.crab_2);break;
            case "crab_3":
                bitmapId = (R.drawable.crab_3);break;
            case "crab_4":
                bitmapId = (R.drawable.crab_4);break;
            case "crab_5":
                bitmapId = (R.drawable.crab_5);break;
        }
        size = 6;
        x=5;
        y= GameViewEvading.maxY - size - 1;
        speed = (float) 2;

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
