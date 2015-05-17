package com.mongooseofbefore.labyrinthofbefore.activities;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.mongooseofbefore.labyrinthofbefore.R;
import com.mongooseofbefore.labyrinthofbefore.guiengine.Helper;

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

        //Remove title bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.main);

        Bitmap[] controlsBitmap = new Bitmap[6];
        controlsBitmap[0] = Helper.getBitmapFromAsset("art/controls/up.png", this);
        controlsBitmap[1] = Helper.getBitmapFromAsset("art/controls/right.png", this);
        controlsBitmap[2] = Helper.getBitmapFromAsset("art/controls/down.png", this);
        controlsBitmap[3] = Helper.getBitmapFromAsset("art/controls/left.png", this);
        controlsBitmap[4] = Helper.getBitmapFromAsset("art/controls/left.png", this);
        controlsBitmap[5] = Helper.getBitmapFromAsset("art/controls/left.png", this);

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
        switch (event.getAction() ) {
            case MotionEvent.ACTION_DOWN: return selectView(view);
            case MotionEvent.ACTION_UP: break;
        }
        return false;
    }

    private boolean selectView(View view){
        if(view == startView){
            Intent intent = new Intent(this, GameActivity.class);
            this.startActivity(intent);

        }
        else if (view == creditsView){
            Intent intent = new Intent(this, CreditsActivity.class);
            this.startActivity(intent);
        }
        else if(view == exitView){
            finish();
        }
        else
            return false;

        return true;
    }
}
