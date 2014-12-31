package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player extends Character{

    private boolean nextLevel_;

    public Player(int x, int y, int d, Bitmap[][] bitmaps) {
        super(x, y, d, bitmaps);
    }

    public void draw(Canvas canvas, Context context){
        int d       = getDirection();
        int xPos    = getXPos();
        int yPos    = getYPos();
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
        setXPos(x);
        setYPos(y);
        setDirection(d);
    }

    protected void move(Map currentLevel, int xPos, int yPos, int direction){
        if(xPos >= 21)
            xPos = 0;
        if(xPos < 0)
            xPos = 20;
        if(yPos >= 21)
            yPos = 0;
        if(yPos < 0)
            yPos = 20;

        setDirection(direction);
        if(currentLevel.getLevel()[xPos][yPos].getType() == 1){
            return;
        }
        setXPos(xPos);
        setYPos(yPos);
        if(currentLevel.getLevel()[xPos][yPos].getType() == 2){
            nextLevel_ = true;
        }
    }

    public boolean nextLevel(){
        return nextLevel_;
    }

    public void resetNextLevel(){
        nextLevel_ = false;
    }
}