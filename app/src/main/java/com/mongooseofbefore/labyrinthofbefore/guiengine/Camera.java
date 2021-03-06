package com.mongooseofbefore.labyrinthofbefore.guiengine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by Aflah on 1/1/2015.
 */
public class Camera {

    int gamePx;

    public Camera(){
    }

    public void draw(Level level, Canvas canvas){
        if(level == null)
            return;
        if(canvas == null)
            return;

        int screenWidth     = canvas.getWidth();
        int screenHeight    = canvas.getHeight();

        gamePx = Math.min(screenWidth, screenHeight);

        int tileMapWidth    = level.current.getWidth();
        int tileMapHeight   = level.current.getHeight();

        RectF tileRect      = new RectF();
        RectF playerRect    = new RectF();

        int tileRectPx = gamePx/ tileMapWidth;

        int offsetX         = (screenWidth - (tileMapWidth * tileRectPx))/2;
        int offsetY         = (screenHeight - (tileMapHeight * tileRectPx))/2;

        int h = 0;
        int w = 0;

        // draws the map array, tile by tile, based on their type
        while (h < level.current.getHeight()) {
            while (w < level.current.getWidth()) {
                tileRect.set((w * tileRectPx) + offsetX, (h * tileRectPx) + offsetY,
                        ((w + 1) * tileRectPx) + offsetX, ((h + 1) * tileRectPx) + offsetY);
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
        playerRect.set((xPos * tileRectPx) + offsetX, (yPos * tileRectPx) + offsetY,
                ((xPos + 1) * tileRectPx) + offsetX, ((yPos + 1) * tileRectPx) + offsetY);
        canvas.drawBitmap(currentSprite, null, playerRect, null);
    }
}
