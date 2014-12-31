package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.graphics.Bitmap;

public abstract class Character {

    private int xPos_;
    private int yPos_;
    private int direction_;
    private Bitmap[][] sprites_;
    public Character(int x, int y, int d, Bitmap[][] bitmaps){
        xPos_       = x;
        yPos_       = y;
        direction_  = d;
        sprites_     = bitmaps;
    }

    public Bitmap[][] getSprites()  {return sprites_;}
    public int getXPos()            {return xPos_;}
    public int getYPos()            {return yPos_;}
    public int getDirection()       {return direction_;}


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
    }

    public void setCharacter(int x, int y, int d){
        setXPos(x);
        setYPos(y);
        setDirection(d);
    }

    protected void setXPos(int xPos)            {
        xPos_      = xPos;
    }
    protected void setYPos(int yPos)            {
        yPos_      = yPos;
    }
    protected void setDirection(int direction)  {
        direction_ = direction;
    }
}
	
	
	

