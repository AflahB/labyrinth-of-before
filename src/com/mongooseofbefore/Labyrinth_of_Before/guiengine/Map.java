package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Map {
    private Tile[][]	tileMap_;
    private int			width;
    private int			height;
    private Bitmap[]    bitmaps_;

    /**
     * initialises the map Instance
     * @param xw the map width in tiles
     * @param yh the map height in tiles
     */
    public Map(int xw, int yh, Bitmap[] bitmaps) {
        tileMap_    = new Tile[xw][yh];
        this.width  = xw - 1;
        this.height = yh - 1;
        bitmaps_ = bitmaps;
    }

    public Tile[][] getTileMap() {
        return tileMap_;
    }

    public void setTileMap(Tile[][] tileMap) {
        this.tileMap_ = tileMap;
    }

    public void paint(Canvas canvas) {
        int h = 0;
        int w = 0;
        int rectwidth = 32;
        int rectheight = 32;
        Rect rect = new Rect();

        // draws the map array, tile by tile, based on their type
        while (h <= height) {
            while (w <= width) {
                rect.set((w * rectwidth) + 24, (h * rectheight) + 24,
                        ((w + 1) * rectwidth) + 24, ((h + 1) * rectheight) + 24);
                if (tileMap_[w][h].getType() == 1) {
                        canvas.drawBitmap(bitmaps_[0], null, rect, null);

                } else {
                        canvas.drawBitmap(bitmaps_[1], null, rect, null);
                }
                w++;
            }
            w = 0;
            h++;
        }
    }
}
