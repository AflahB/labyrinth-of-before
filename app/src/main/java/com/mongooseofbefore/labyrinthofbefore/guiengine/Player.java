package com.mongooseofbefore.labyrinthofbefore.guiengine;

import android.graphics.Bitmap;

public class Player extends Character{

    private boolean nextLevel_;
    private int steps_;

    public Player(int x, int y, int d, Bitmap[][] bitmaps) {
        super(x, y, d, bitmaps);
        steps_ = 0;
    }

    public void move(Map currentLevel, int direction){
        int xPos = getXPos();
        int yPos = getYPos();
        setDirection(direction);

        switch(direction){
            case 0:
                yPos--;
                break;
            case 1:
                xPos++;
                break;
            case 2:
                yPos++;
                break;
            case 3:
                xPos--;
                break;
        }

        if(xPos >= 21)
            xPos = 0;
        if(xPos < 0)
            xPos = 20;
        if(yPos >= 21)
            yPos = 0;
        if(yPos < 0)
            yPos = 20;

        if(currentLevel.getTileMap()[xPos][yPos].getType()!= 1){
            setXPos(xPos);
            setYPos(yPos);
        }
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