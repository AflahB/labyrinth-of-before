package com.mongooseofbefore.Labyrinth_of_Before.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import com.example.Labyrinth_of_Before.R;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameThread;

public class GameActivity extends FragmentActivity implements OnTouchListener {
    ImageView   upView;
    ImageView   leftView;
    ImageView   downView;
    ImageView   rightView;
    Button      flip;

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
        GameThread.GameEngine().mapFlip();
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (event.getAction() ) {
            case MotionEvent.ACTION_DOWN: return movePlayer(view);
            case MotionEvent.ACTION_UP: break;
        }
        return false;
    }

    private boolean movePlayer(View v){
        if (v == upView) {
            GameEngine.movePlayer(0);
        }
        else if (v == leftView) {
            GameEngine.movePlayer(3);
        }
        else if (v == downView) {
            GameEngine.movePlayer(2);
        }
        else if (v == rightView) {
            GameEngine.movePlayer(1);
            return true;
        }
        else
            return false;

        return true;
    }
}