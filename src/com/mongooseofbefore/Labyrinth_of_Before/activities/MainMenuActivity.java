package com.mongooseofbefore.Labyrinth_of_Before.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.example.Labyrinth_of_Before.R;
import com.mongooseofbefore.Labyrinth_of_Before.guiengine.Helper;

public class MainMenuActivity extends Activity implements OnTouchListener {

    ImageView bkgView;
    ImageView titleView;
    ImageButton startView;
    ImageButton optionsView;
    ImageButton creditsView;
    ImageButton exitView;

    /**
     * Called when the activity is first created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        bkgView     = (ImageView) findViewById(R.id.imageViewBackground);
        bkgView.setImageBitmap(Helper.getBitmapFromAsset("art/menu/bgn.png", this));

        titleView   = (ImageView) findViewById(R.id.imageViewTitle);
        titleView.setImageBitmap(Helper.getBitmapFromAsset("art/menu/title.png", this));
        titleView.setOnTouchListener(this);

        startView   = (ImageButton) findViewById(R.id.imageButtonStart);
        startView.setImageBitmap(Helper.getBitmapFromAsset("art/menu/start.png", this));
        startView.setOnTouchListener(this);

        optionsView = (ImageButton) findViewById(R.id.imageButtonOptions);
        optionsView.setImageBitmap(Helper.getBitmapFromAsset("art/menu/options.png", this));
        optionsView.setOnTouchListener(this);

        creditsView = (ImageButton) findViewById(R.id.imageButtonCredits);
        creditsView.setImageBitmap(Helper.getBitmapFromAsset("art/menu/credits.png", this));
        creditsView.setOnTouchListener(this);

        exitView    = (ImageButton) findViewById(R.id.imageButtonExit);
        exitView.setImageBitmap(Helper.getBitmapFromAsset("art/menu/exit.png", this));
        exitView.setOnTouchListener(this);
    }

    public boolean onTouch(View view, MotionEvent event){
        if(view == startView){
            Intent intent = new Intent(this, GameActivity.class);
            this.startActivity(intent);
        }
        if(view == exitView){
            this.finish();
        }
        return false;
    }
}