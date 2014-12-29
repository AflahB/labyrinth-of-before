package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;

public abstract class Character {

    private int xPos_;
    private int yPos_;
    private int direction_;

    public Character(int x, int y, int d){
        xPos_       = x;
        yPos_       = y;
        direction_  = d;
    }

    public int getXPos()        {return xPos_;}
    public int getYPos()        {return yPos_;}
    public int getDirection()   {return direction_;}

    public void moveUp(Map currentlevel){
        move(currentlevel, getXPos(),       getYPos() - 1,  1);}

    public void moveDown(Map currentlevel){
        move(currentlevel, getXPos(),       getYPos() + 1,  3);
    }
    public void moveLeft(Map currentlevel){
        move(currentlevel, getXPos() - 1,   getYPos(),      4);
    }
    public void moveRight(Map currentlevel){
        move(currentlevel, getXPos() + 1,   getYPos(),      2);
    }

    public void draw(Canvas canvas, Context context){
        int d       = this.getDirection();
        int xPos    = this.getXPos();
        int yPos    = this.getYPos();
        Bitmap bitmap0;
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

    protected void setXPos(int xPos)            {xPos_      = xPos;}
    protected void setYPos(int yPos)            {yPos_      = yPos;}
    protected void setDirection(int direction)  {direction_ = direction;}

    protected void move(Map currentLevel, int xPos, int yPos, int direction){
        direction_ = direction;
        if(currentLevel.getLevel()[xPos][yPos].getType()!=0){
            xPos_ = xPos;
            yPos_ = yPos;
        }
    }
}
	
	
	
