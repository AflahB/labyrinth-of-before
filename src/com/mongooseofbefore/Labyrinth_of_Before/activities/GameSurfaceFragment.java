package com.mongooseofbefore.Labyrinth_of_Before.activities;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.view.*;
import android.widget.Toast;
import com.mongooseofbefore.Labyrinth_of_Before.R;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameThread;

/**
 * Created by Aflah on 12/30/2014.
 */
public class GameSurfaceFragment extends Fragment implements SurfaceHolder.Callback {

    //game engine variables
    GameEngine gameEngine;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder_;
    Context context;
    private GameThread gameThread;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.gamefragment, container, false);

        surfaceView = (SurfaceView) v.findViewById(R.id.surfaceViewGame);
        surfaceHolder_ = surfaceView.getHolder();

        surfaceHolder_.addCallback(this);

        // deprecated setting, but required on Android versions prior to 3.0
        //surfaceHolder_.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS)

        context = v.getContext();
        gameEngine = new GameEngine();
        gameEngine.init(context);
        gameThread = new GameThread(surfaceHolder_, context, new Handler(), gameEngine);

        if(savedInstanceState != null){

            int playerPositionX = savedInstanceState.getInt("playerPosX");
            int playerPositionY = savedInstanceState.getInt("playerPosY");
            int playerDirection = savedInstanceState.getInt("playerDir");

            gameEngine.getLevel().getPlayer().setCharacter(playerPositionX, playerPositionY, playerDirection);
        }

        return v;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.
        int playerPositionX = gameEngine.getLevel().getPlayer().getXPos();
        int playerPositionY = gameEngine.getLevel().getPlayer().getYPos();
        int playerDirection = gameEngine.getLevel().getPlayer().getDirection();

        savedInstanceState.putInt("playerPosX", playerPositionX);
        savedInstanceState.putInt("playerPosY", playerPositionY);
        savedInstanceState.putInt("playerDir", playerDirection);
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
                Toast.makeText(context, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
        }
    }

    /**
     * creates the view
     */
    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        if (gameThread.getState() == GameThread.State.TERMINATED) {
            gameThread = new GameThread(surfaceHolder_, context, new Handler(), gameEngine);
        }
        gameThread.start();
    }

    /**
     * changes the view
     */
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }
}