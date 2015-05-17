/*
 *  File:       Reader.java - A basic file reader
 *
 *  OD:         Aflah
 *  License:    It's all good homie.
 *  Issues:     This only reads in a file and stores it into a list. There is no implementation for it to create any game object as of yet.
 *  			The files need to be hard-coded in as there is no file dialogue implementation that allows users to select their own files.
 */
package com.mongooseofbefore.labyrinthofbefore.guiengine;

import android.graphics.Bitmap;

public class Level {

    public  Map         current;
    public  Map         flip;
    private Player      player_;
    private Boss        boss_;
    private Bitmap[][]  bossSprites_;
    private final int   size = 21;

    public Level(Object[] objects, Bitmap[] currentBitmaps, Bitmap[] flipBitmaps){
        current     = new Map(size,size, currentBitmaps);
        flip        = new Map(size,size, flipBitmaps);
        player_     = new Player(0, 0 ,0, new Bitmap[4][4]);
        loadLevel(objects);
    }
//    For possible future procedural map generation
//    public Level(Object[] objects, int x, int y, Bitmap[] currentBitmaps, Bitmap[] flipBitmaps){
//        current     = new Map(x,y, currentBitmaps);
//        flip        = new Map(x,y, flipBitmaps);
//        player_     = new Player(0, 0 ,0, new Bitmap[4][4]);
//        loadLevel(objects);
//    }
    public Level(Object[] objects, Bitmap[] currentBitmaps, Bitmap[] flipBitmaps, Bitmap[][] playerSprites){
        current     = new Map(21,21, currentBitmaps);
        flip        = new Map(21,21, flipBitmaps);
        player_     = new Player(0, 0 ,0, playerSprites);
        loadLevel(objects);
    }
    public Level(Object[] objects, Bitmap[] currentBitmaps, Bitmap[] flipBitmaps, Bitmap[][] playerSprites, Bitmap[][] bossSprites){
        current     = new Map(21,21, currentBitmaps);
        flip        = new Map(21,21, flipBitmaps);
        player_     = new Player(0, 0 ,0, playerSprites);
        bossSprites_= bossSprites;
        loadLevel(objects);
    }

    public Boss getBoss(){
        return boss_;
    }

    public Player getPlayer(){
        return player_;
    }

	private void loadLevel(Object[] levelObjects) {
        current.setTileMap((Tile[][]) levelObjects[0]);
        flip.setTileMap((Tile[][]) levelObjects[1]);
        int[] playerPosition    = (int[]) levelObjects[2];

        player_.setCharacter(playerPosition[0], playerPosition[1], playerPosition[2]);

        if(levelObjects[3] != null) {
            boss_                   = new Boss(0, 0 ,0, bossSprites_);
            int[] bossPosition      = (int[]) levelObjects[3];
            boss_.setCharacter(bossPosition[0], bossPosition[1], bossPosition[2]);
        }
	}
}

