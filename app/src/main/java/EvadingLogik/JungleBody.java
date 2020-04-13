package EvadingLogik;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

public class JungleBody {
    protected float x; // координаты
    protected float y;
    protected float size; // размер
    protected float speed; // скорость
    protected int bitmapId; // id картинки
    protected Bitmap bitmap; // картинка

    void init(Context context) { // сжимаем картинку до нужных размеров
        Bitmap cBitmap = BitmapFactory.decodeResource(context.getResources(), bitmapId);
        bitmap = Bitmap.createScaledBitmap(
                cBitmap, (int)(size * GameViewEvading.unitW), (int)(size * GameViewEvading.unitH), false);
        cBitmap.recycle();
    }

    void update(){ // тут будут вычисляться новые координаты
    }

    void drow(Paint paint, Canvas canvas){ // рисуем картинку
        canvas.drawBitmap(bitmap, x* GameViewEvading.unitW, y* GameViewEvading.unitH, paint);
    }
}
