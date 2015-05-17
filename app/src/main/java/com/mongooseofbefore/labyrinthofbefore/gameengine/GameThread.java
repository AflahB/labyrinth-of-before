package com.mongooseofbefore.labyrinthofbefore.gameengine;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    //Variables defining the game engines properties
    private final SurfaceHolder surfaceHolder_;
    private long overSleepTime = 0;
    private long excess = 0;

    public final static int RUNNING = 1;
    public final static int PAUSED = 2;
    private static final int MAX_FRAME_SKIPS = 5;
    public int state = RUNNING;

    //the current game engine
    public static GameEngine gameEngine_;

    /**
     * Starts the game engine thread, and setting the surface
     * @param surfaceHolder
     * @param context
     * @param handler
     * @param gameEngine
     */
    public GameThread(SurfaceHolder surfaceHolder, Context context,
                      Handler handler, GameEngine gameEngine) {
        surfaceHolder_ = surfaceHolder;
        gameEngine_ = gameEngine;
    }

    /**
     * The game engine handler
     */
    @Override
    public void run() {
        long delay = 1000000000L / 25;
        long beforeTime;
        long afterTime;
        long timeDiff;
        long sleepTime;
        Canvas canvas;

        //draw frames while the game engine is enabled
        while (state == RUNNING) {
            beforeTime = System.nanoTime();

            //buffer the frame
            gameEngine_.update();

            //handle the drawing of the frames
            canvas = null;
            try {
                canvas = surfaceHolder_.lockCanvas(null);
                synchronized (surfaceHolder_) {
                    gameEngine_.drawMap(canvas);
                }
            } finally {
                if (canvas != null) {
                    surfaceHolder_.unlockCanvasAndPost(canvas);
                }
            }

            //synchronizes the game engine and framerate with the system clock
            afterTime = System.nanoTime();
            timeDiff = afterTime - beforeTime;
            sleepTime = ((delay) - timeDiff) - overSleepTime;
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime / 1000000L);
                } catch (InterruptedException ex) {
                }
                overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
            } else {
                excess -= sleepTime;
                overSleepTime = 0L;
            }

            //calculates frameskips
            int skips = 0;
            while ((excess > delay) && (skips < MAX_FRAME_SKIPS)) {
                excess -= delay;
                gameEngine_.update();
                skips++;
            }
        }
    }

    public static GameEngine GameEngine(){
        return gameEngine_;
    }
}