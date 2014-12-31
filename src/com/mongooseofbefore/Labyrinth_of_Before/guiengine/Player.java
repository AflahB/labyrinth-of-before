package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Player extends Character{

    private boolean nextLevel_;
    private int steps_;

    public Player(int x, int y, int d, Bitmap[][] bitmaps) {
        super(x, y, d, bitmaps);
        steps_ = 0;
    }

    public void draw(Canvas canvas){
        int d       = getDirection();
        int step    = getSteps()%4;
        int xPos    = getXPos();
        int yPos    = getYPos();
        Rect rect   = new Rect((xPos * 32)+24, (yPos * 32)+24, ((xPos+1) * 32)+24, ((yPos+1) * 32)+24);
        canvas.drawBitmap(getSprites()[d][step], null, rect, null);
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
        if(currentLevel.getTileMap()[xPos][yPos].getType() == 1){
            return;
        }
        setXPos(xPos);
        setYPos(yPos);
        steps_++;
        if(currentLevel.getTileMap()[xPos][yPos].getType() == 2){
            nextLevel_ = true;
        }
    }

    public int getSteps(){return steps_;}

    public boolean nextLevel(){
        return nextLevel_;
    }

    public void resetNextLevel(){
        nextLevel_ = false;
    }
}