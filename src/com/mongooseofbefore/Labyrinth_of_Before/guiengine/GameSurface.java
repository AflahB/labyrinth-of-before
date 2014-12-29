/*
 * File:		GameSurface.java - Handles the surface view (UI) for the game screen
 * 
 * Originator:	Ryan
 * Developers:	Ryan
 * Copyright:
 * License:
 * 
 * Notes:		100% working
 * Issues:
 * Reference:
 * Implements:
 */

package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameThread;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Game surface view
 * @author rnl3
 *
 */
public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    //game engine variables
    GameEngine gameEngine;
    SurfaceHolder surfaceHolder;
    Context context;
    private GameThread gameThread;

    /**
     * GameSurface constructor. Creates the view.
     * @param context
     * @param attrs
     * @param defStyle
     */
    public GameSurface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
        initView();
    }

    /**
     * Alternate GameSurface constructor
     * @param context
     * @param attrs
     */
    public GameSurface(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView();
    }

    /**
     * Initializes the view
     */
    void initView() {
        SurfaceHolder surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        gameEngine = new GameEngine();
        gameEngine.init(context);
        gameThread = new GameThread(surfaceHolder, context, new Handler(),
                gameEngine);
        setFocusable(true);
    }

    /**
     * handles pausing and closing the application
     */
    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        boolean retry = true;
        gameThread.state = GameThread.PAUSED;
        while (retry) {
            try {
                gameThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    /**
     * creates the view
     */
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        if (gameThread.state == GameThread.PAUSED) {
            gameThread = new GameThread(getHolder(), context, new Handler(),
                    gameEngine);
            gameThread.start();
        } else {
            gameThread.start();
        }
    }

    /**
     * changes the view
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        gameEngine.setSurfaceDimensions(width, height);
    }

}