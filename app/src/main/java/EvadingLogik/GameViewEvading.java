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

import com.example.rabgame.ChooseTypeOfGame;
import com.example.rabgame.R;

import java.util.ArrayList;

public class GameViewEvading extends SurfaceView implements Runnable {
    public static int maxX = 32;
    public static int maxY = 24;
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
    private final int COCOUNT_INTERVAL = 17;
    private int currentTime = 0;
    private Bitmap bitmap;
    private Boolean cheker = false;


    public GameViewEvading(Context context) {
        super(context);
        //инициализируем обьекты для рисования
        surfaceHolder = getHolder();
        paint = new Paint();
        this.context = context;

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

            canvas.drawColor(Color.WHITE);


            Bitmap cBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.background_hor);
            if (!cheker) {
                bitmap = Bitmap.createScaledBitmap(
                        cBitmap, (int) (maxX * GameViewEvading.unitW), (int) (maxY * GameViewEvading.unitH), false);
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
            gameThread.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void checkCollision() {
        for (Сoconut asteroid : cocounts) {
            if (asteroid.isCollision(crab.x, crab.y, crab.size)) {
                gameRunning = false;
                Intent intent = new Intent(context, ChooseTypeOfGame.class);
                intent.putExtra("game", "EVADE");
                intent.putExtra("coins", "10");
                context.startActivity(intent);

            }
        }
    }

    private void checkIfNewAsteroid() {
        if (currentTime >= COCOUNT_INTERVAL) {
            Сoconut asteroid = new Сoconut(getContext());
            cocounts.add(asteroid);
            currentTime = 0;
        } else {
            currentTime++;
        }
    }
}
