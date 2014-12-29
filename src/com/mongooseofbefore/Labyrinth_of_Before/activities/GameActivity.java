package com.mongooseofbefore.Labyrinth_of_Before.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import com.example.Labyrinth_of_Before.R;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameThread;

public class GameActivity extends FragmentActivity implements OnTouchListener {
    ImageView upView;
    ImageView leftView;
    ImageView downView;
    ImageView rightView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        Intent intent = getIntent();

        upView = (ImageView) findViewById(R.id.imageViewUp);
        upView.setOnTouchListener(this);
        leftView = (ImageView) findViewById(R.id.imageViewLeft);
        leftView.setOnTouchListener(this);
        downView = (ImageView) findViewById(R.id.imageViewDown);
        downView.setOnTouchListener(this);
        rightView = (ImageView) findViewById(R.id.imageViewRight);
        rightView.setOnTouchListener(this);



    }

    // flip method called on device rotation
    public void flip(View view) {
        GameThread.gameEngine.mapFlip();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction() ) {
            case MotionEvent.ACTION_DOWN: return movePlayer(v);
            case MotionEvent.ACTION_UP: break;
        }
        return false;
    }

    private boolean movePlayer(View v){
        if (v == upView) {
            GameEngine.movePlayerUp();
            return true;
        }
        if (v == leftView) {
            GameEngine.movePlayerLeft();
            return true;
        }
        if (v == downView) {
            GameEngine.movePlayerDown();
            return true;
        }
        if (v == rightView) {
            GameEngine.movePlayerRight();
            return true;
        }
        return false;
    }
}