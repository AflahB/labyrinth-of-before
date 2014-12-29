package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;

public class Player extends Character{

    public Player(int x, int y, int d) {
        super(x, y, d);
    }

    public void draw(Canvas canvas, Context context){
        int d       = this.getDirection();
        int xPos    = this.getXPos();
        int yPos    = this.getYPos();
        Bitmap  bitmap0;
        Rect rect   = new Rect((xPos * 32)+24, (yPos * 32)+24, ((xPos+1) * 32)+24, ((yPos+1) * 32)+24);
        if(d==1)
        {
            bitmap0 = Helper.getBitmapFromAsset("art/menu/bgn.png", context);
            canvas.drawBitmap(bitmap0, null, rect, null);

        }
        else if(d==2)
        {
            bitmap0 = Helper.getBitmapFromAsset("art/menu/bgn.png", context);
            canvas.drawBitmap(bitmap0, null, rect, null);

        }
        else if(d==3)
        {
            bitmap0 = Helper.getBitmapFromAsset("art/menu/bgn.png", context);
            canvas.drawBitmap(bitmap0, null, rect, null);
        }
        else if(d==4)
        {
            bitmap0 = Helper.getBitmapFromAsset("art/menu/bgn.png", context);
            canvas.drawBitmap(bitmap0, null, rect, null);
        }

    }

    public void setPlayer(int x, int y, int d){
        this.setXPos(x);
        this.setYPos(y);
        this.setDirection(d);
    }

    protected void move(Map currentLevel, int xPos, int yPos, int direction){
        setDirection(direction);
        if(currentLevel.getLevel()[xPos][yPos].getType()!=1){
            yPos--;
            if(currentLevel.getLevel()[xPos][yPos].getType() == 2){
                GameEngine.nextLevel();
            }
        }
    }
}