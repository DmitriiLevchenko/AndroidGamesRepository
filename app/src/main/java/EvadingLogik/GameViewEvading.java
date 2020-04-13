package EvadingLogik;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.rabgame.R;

import java.util.ArrayList;

public class GameViewEvading extends SurfaceView implements Runnable {
    public static int maxX = 32; // размер по горизонтали
    public static int maxY = 24;
    public static float unitW = 0; // пикселей в юните по горизонтали
    public static float unitH = 0; // пикселей в юните по вертикали
    private boolean firstTime = true;
    private boolean gameRunning = true;
    private Crab ship;
    private Thread gameThread = null;
    private Paint paint;
    private Canvas canvas;
    private SurfaceHolder surfaceHolder;
    private ArrayList<Сoconut> asteroids = new ArrayList<>(); // тут будут харанится астероиды
    private final int ASTEROID_INTERVAL = 50; // время через которое появляются астероиды (в итерациях)
    private int currentTime = 0;
    public GameViewEvading(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();

        // инициализируем поток
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while (gameRunning) {
            update();
            draw();
            checkCollision();
            checkIfNewAsteroid();
            control();
        }
    }

    private void update() {
        if(!firstTime) {
            ship.update();
            for (Сoconut asteroid : asteroids) {
                asteroid.update();
            }
        }
    }

    private void draw() {
        if (surfaceHolder.getSurface().isValid()) {  //проверяем валидный ли surface

            if(firstTime){ // инициализация при первом запуске
                firstTime = false;
                unitW = surfaceHolder.getSurfaceFrame().width()/maxX; // вычисляем число пикселей в юните
                unitH = surfaceHolder.getSurfaceFrame().height()/maxY;

                ship = new Crab(getContext()); // добавляем корабль
            }


            canvas = surfaceHolder.lockCanvas(); // закрываем canvas
            canvas.drawColor(Color.WHITE); // заполняем фон чёрным
            //canvas.drawCircle(unitW ,unitH, unitH/4, paint);
            Bitmap cBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_hor);
            Bitmap bitmap = Bitmap.createScaledBitmap(
                   cBitmap, (int)(maxX * GameViewEvading.unitW), (int)(maxY * GameViewEvading.unitH), false);
            canvas.drawBitmap(bitmap,0,0,null);//фо

            ship.drow(paint, canvas); // рисуем корабль

            for(Сoconut asteroid: asteroids){ // рисуем астероиды
                asteroid.drow(paint, canvas);
            }
            surfaceHolder.unlockCanvasAndPost(canvas); // открываем canvas
        }
    }

    private void control() { // пауза на 17 миллисекунд
        try {
            gameThread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    private void checkCollision(){ // перебираем все астероиды и проверяем не касается ли один из них корабля
        for (Сoconut asteroid : asteroids) {
            if(asteroid.isCollision(ship.x, ship.y, ship.size)){
                // игрок проиграл
                gameRunning = false; // останавливаем игру
                // TODO добавить анимацию взрыва
            }
        }
    }

    private void checkIfNewAsteroid(){ // каждые 50 итераций добавляем новый астероид
        if(currentTime >= ASTEROID_INTERVAL){
            Сoconut asteroid = new Сoconut(getContext());
            asteroids.add(asteroid);
            currentTime = 0;
        }else{
            currentTime ++;
        }
    }
}
