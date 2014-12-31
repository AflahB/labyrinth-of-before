package com.mongooseofbefore.Labyrinth_of_Before.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import com.example.Labyrinth_of_Before.R;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameThread;

public class GameActivity extends FragmentActivity implements OnTouchListener {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Intent intent = getIntent();

        findViewById(R.id.imageViewUp).setOnTouchListener(this);
        findViewById(R.id.imageViewLeft).setOnTouchListener(this);
        findViewById(R.id.imageViewDown).setOnTouchListener(this);
        findViewById(R.id.imageViewRight).setOnTouchListener(this);

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
                return true;
            case R.id.imageViewRight:
                return true;
            case R.id.imageViewDown:
                return true;
            case R.id.imageViewLeft:
                return true;
            default:
                return false;
        }
    }
}