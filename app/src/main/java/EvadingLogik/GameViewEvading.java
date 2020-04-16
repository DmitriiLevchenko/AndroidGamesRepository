package EvadingLogik;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.rabgame.ChooseTypeOfGame;
import com.example.rabgame.CustomizedUser;
import com.example.rabgame.IGameViewEvading;
import com.example.rabgame.R;

import java.util.ArrayList;
import java.util.logging.Handler;
public class GameViewEvading extends SurfaceView implements Runnable{
    public static int maxX = 32;
    public static int maxY = 20;
    public static float unitW = 0;
    public static float unitH = 0;
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Crab crab;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private Context context;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Сoconut> cocounts = new ArrayList<>();
    private  int COCOUNT_INTERVAL = 12;
    private int currentTime = 0;
    private Bitmap bitmap;
    private Boolean cheker = false;
    IGameViewEvading iGameViewEvading;
    Handler h;


    public GameViewEvading(Context context, IGameViewEvading iGameViewEvading) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        this.context = context;
        iGameViewEvading = iGameViewEvading;
        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkIfNewAsteroid();
            control();
            checkCollision();
        }
    }

    private void update() {
        if (!firstTime) {
            crab.update();
            for (Сoconut asteroid : cocounts) {
                asteroid.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {
            if (firstTime) {
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width() / maxX;
                unitH = surfaceHolder.getSurfaceFrame().height() / maxY;
                crab = new Crab(getContext());
            }
            canvas = surfaceHolder.lockCanvas();

            Bitmap cBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_hor);
            if (!cheker) {
                bitmap = Bitmap.createScaledBitmap(
                        cBitmap, surfaceHolder.getSurfaceFrame().width(), surfaceHolder.getSurfaceFrame().height(), false);
                canvas.drawBitmap(bitmap, 0, 0, null);//фо
            }
            crab.drow(paint, canvas);

            for (Сoconut asteroid : cocounts) {
                asteroid.drow(paint, canvas);
            }
            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    private void control() {
        try {
            gameThread.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkCollision() {
        ArrayList<Сoconut> arrayList = new ArrayList<>();
        for (Сoconut сoconut : cocounts) {
            if (сoconut.isCollision(crab.x, crab.y, crab.size)) {
                gameRunning = false;
                iGameViewEvading.checkCollision();

            }
            if(сoconut.isEnd(surfaceHolder.getSurfaceFrame().height(),unitH))
            {
                arrayList.add(сoconut);
                CustomizedUser.coins += 1;
            }
        }
        for(int i = 0;i< arrayList.size();i++)
        {
            cocounts.remove(arrayList.get(i));
        }
    }

    private void checkIfNewAsteroid() {
        if (currentTime >= COCOUNT_INTERVAL)
        {
            Сoconut asteroid = new Сoconut(getContext());
            cocounts.add(asteroid);
            currentTime = 5;
        } else {
            currentTime++;
        }
    }
}
