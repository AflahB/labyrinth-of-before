package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by Aflah on 1/1/2015.
 */
public class Camera {

    public Camera(){
    }

    public void draw(Level level, Canvas canvas){
        int screenWidth_ = canvas.getWidth();
        //int screenHeight_= canvas.getHeight();

        RectF tileRect = new RectF();
        RectF playerRect = new RectF();

        int h = 0;
        int w = 0;
        int tileRectWidth = screenWidth_/ level.current.getWidth();
        int tileRectHeight = tileRectWidth;


        // draws the map array, tile by tile, based on their type
        while (h < level.current.getHeight()) {
            while (w < level.current.getWidth()) {
                tileRect.set((w * tileRectHeight), (h * tileRectHeight),
                        ((w + 1) * tileRectWidth), ((h + 1) * tileRectHeight));
                if (level.current.getTileMap()[w][h].getType() == 1) {
                        canvas.drawBitmap(level.current.getBitmaps()[0], null, tileRect, null);

                } else {
                        canvas.drawBitmap(level.current.getBitmaps()[1], null, tileRect, null);
                }
                w++;
            }
            w = 0;
            h++;
        }

        int d       = level.getPlayer().getDirection();
        int step    = level.getPlayer().getSteps()%4;
        int xPos    = level.getPlayer().getXPos();
        int yPos    = level.getPlayer().getYPos();
        Bitmap currentSprite  = level.getPlayer().getSprites()[d][step];
        playerRect.set((xPos * tileRectWidth), (yPos * tileRectHeight), ((xPos + 1) * tileRectWidth), ((yPos + 1) * tileRectHeight));
        canvas.drawBitmap(currentSprite, null, playerRect, null);


    }
}
