package com.mongooseofbefore.Labyrinth_of_Before.activities;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.Toast;
import com.example.Labyrinth_of_Before.R;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameThread;

/**
 * Created by Aflah on 12/30/2014.
 */
public class GameSurfaceFragment extends Fragment implements SurfaceHolder.Callback {

    //game engine variables
    GameEngine gameEngine;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    Context context;
    private GameThread gameThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.gamefragment, container, false);

        surfaceView = (SurfaceView) v.findViewById(R.id.surfaceViewGame);
        surfaceHolder = surfaceView.getHolder();
        context = v.getContext();
        gameEngine = new GameEngine();
        gameEngine.init(context);
        gameThread = new GameThread(surfaceHolder, context, new Handler(), gameEngine);

        return v;
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
                Toast.makeText(getActivity(), "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    /**
     * creates the view
     */
    @Override
    public void surfaceCreated(SurfaceHolder arg0) {
        if (gameThread.state == GameThread.PAUSED) {
            gameThread = new GameThread(surfaceHolder, context, new Handler(),
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