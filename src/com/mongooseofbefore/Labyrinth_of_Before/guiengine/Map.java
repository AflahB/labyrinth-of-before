package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.graphics.Bitmap;

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
        this.width  = xw;
        this.height = yh;
        bitmaps_ = bitmaps;
    }


    public Bitmap[] getBitmaps(){return bitmaps_;}

    public int getWidth(){return width;}
    public int getHeight(){return height;}

    public Tile[][] getTileMap() {
        return tileMap_;
    }

    public void setTileMap(Tile[][] tileMap) {
        this.tileMap_ = tileMap;
    }
}
