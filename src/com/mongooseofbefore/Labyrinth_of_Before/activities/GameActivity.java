package com.mongooseofbefore.Labyrinth_of_Before.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import com.mongooseofbefore.Labyrinth_of_Before.R;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameThread;
import com.mongooseofbefore.Labyrinth_of_Before.guiengine.Helper;

public class GameActivity extends FragmentActivity implements OnTouchListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        Window w = this.getWindow();

        //Remove notification bar
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.game);

        ImageView up = (ImageView) findViewById(R.id.imageViewUp);
        ImageView right = (ImageView) findViewById(R.id.imageViewRight);
        ImageView down = (ImageView) findViewById(R.id.imageViewDown);
        ImageView left = (ImageView) findViewById(R.id.imageViewLeft);

        up.setOnTouchListener(this);
        right.setOnTouchListener(this);
        down.setOnTouchListener(this);
        left.setOnTouchListener(this);

        up.setImageBitmap(Helper.getBitmapFromAsset("art/controls/up.png", this));
        right.setImageBitmap(Helper.getBitmapFromAsset("art/controls/right.png", this));
        down.setImageBitmap(Helper.getBitmapFromAsset("art/controls/down.png", this));
        left.setImageBitmap(Helper.getBitmapFromAsset("art/controls/left.png", this));
    }


    // flip method called on device rotation
    public void flip(View view) {
        GameThread.GameEngine().mapFlip();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() ) {
            case MotionEvent.ACTION_DOWN: return movePlayer(view);
            case MotionEvent.ACTION_UP: return false;
            default: return false;
        }
    }

    private boolean movePlayer(View v){
        switch (v.getId()){
            case R.id.imageViewUp:
                GameEngine.movePlayer(0);
                return true;
            case R.id.imageViewRight:
                GameEngine.movePlayer(1);
                return true;
            case R.id.imageViewDown:
                GameEngine.movePlayer(2);
                return true;
            case R.id.imageViewLeft:
                GameEngine.movePlayer(3);
                return true;
            default:
                return false;
        }
    }
}