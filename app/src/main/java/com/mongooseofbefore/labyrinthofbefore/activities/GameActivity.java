package com.mongooseofbefore.labyrinthofbefore.activities;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.mongooseofbefore.labyrinthofbefore.R;
import com.mongooseofbefore.labyrinthofbefore.gameengine.GameEngine;
import com.mongooseofbefore.labyrinthofbefore.gameengine.GameThread;
import com.mongooseofbefore.labyrinthofbefore.guiengine.Helper;

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

        Bitmap[] controlsBitmap = new Bitmap[4];
        controlsBitmap[0] = Helper.getBitmapFromAsset("art/controls/up.png", this);
        controlsBitmap[1] = Helper.getBitmapFromAsset("art/controls/right.png", this);
        controlsBitmap[2] = Helper.getBitmapFromAsset("art/controls/down.png", this);
        controlsBitmap[3] = Helper.getBitmapFromAsset("art/controls/left.png", this);

        int width = 0;
        int height = 0;

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        switch (metrics.densityDpi){
            case DisplayMetrics.DENSITY_LOW:
                width   = 20;
                height  = 20;
                break;
            case DisplayMetrics.DENSITY_MEDIUM:
                width   = 50;
                height  = 50;
                break;
            case DisplayMetrics.DENSITY_HIGH:
                width   = 80;
                height  = 80;
                break;
            case DisplayMetrics.DENSITY_XHIGH:
                width   = 100;
                height  = 100;
                break;
            default:
                width   = 60;
                height  = 60;
                break;
        }

        for(int i = 0; i <= 3; i++){
            controlsBitmap[i] = Bitmap.createScaledBitmap(controlsBitmap[i], width, height, true);
        }

        up.setImageBitmap(controlsBitmap[0]);
        right.setImageBitmap(controlsBitmap[1]);
        down.setImageBitmap(controlsBitmap[2]);
        left.setImageBitmap(controlsBitmap[3]);
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