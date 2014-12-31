/*
 * File:		GameEngine.java - Game engine instance
 *
 * Originator:	Ryan
 * Developers:	Ryan, Luke, Alex
 * Copyright:
 * License:
 *
 * Notes:		100% working
 * Issues:
 * Reference:
 * Implements:
 */

package com.mongooseofbefore.Labyrinth_of_Before.gameengine;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import com.mongooseofbefore.Labyrinth_of_Before.guiengine.Boss;
import com.mongooseofbefore.Labyrinth_of_Before.guiengine.CSVReader;
import com.mongooseofbefore.Labyrinth_of_Before.guiengine.Map;
import com.mongooseofbefore.Labyrinth_of_Before.guiengine.Player;

public class GameEngine {

    public  float   screenWidth;
    public  float   screenHeight;

    // creates a map and canvas
//    Canvas      canvas;
    private static  int     level_;
    public  static  Map     currentTileMap  = new Map(21,21);
    public  static  Map     flipTileMap     = new Map(21,21);
    private static  Map     temp_;
    public  static  Boolean flipped         = false;
    public  static  Player  player;
    public  static  Boss    boss;
    private static  Context context_;

    /**
     * Initializes the Game Engine, Creating the two Map Objects and Five PaintBrushes
     * @param context
     */
    public void init(Context context) {
        level_      = 1;
        setSurfaceDimensions(240, 160);
        context_    = context;
        currentTileMap.init(context);
        //setTheme(paintBlack, paintWhite);
        player      = new Player(0, 0, 0, new Bitmap[4][4]);
        //Loads current level from CSV
        load(level_);
    }

    public void load(int level){
        String  path    = "";
        switch(level){
            case 1:     path    = "levels/debug.csv";
                break;
            case 2:     path    = "levels/hadespalace.csv";
                break;
            default:
                break;
        }
        CSVReader.loadLevel(path, currentTileMap, flipTileMap, context_);
    }

    public void nextLevel(){
        level_++;
        load(level_);
    }

    /**
     * Sets the surface size of the game view
     * @param width
     * @param height
     */
    public void setSurfaceDimensions(int width, int height) {
        screenWidth     = width;
        screenHeight    = height;
    }


    public void update(){
        if(player.nextLevel()) {
            nextLevel();
            player.resetNextLevel();
        }
    }

    /**
     * Draws the map on the canvas along with the player and also the boss
     * @param canvas
     */
    public void drawMap(Canvas canvas) {
        currentTileMap.paint(canvas);		//Calls the paint method in the map class, to draw the frame
        player.draw(canvas, context_);
        //boss.draw(canvas, paintRed);
    }

    /**
     * Flips the level, changing the tile themes
     */
    public void mapFlip(){
        int playerX = player.getXPos();
        int playerY = player.getYPos();

        if(flipTileMap.getLevel()[playerX][playerY].getType() == 0){
            temp_           = currentTileMap;	//Backs up the current level so that changes can be made to it
            currentTileMap  = flipTileMap;		//Sets the current level to the alternate level
            flipTileMap     = temp_;	//Restores the previous level to the the alternate level

            //Selects the the theme type based on the the current level state
            if (flipped){
                changeTheme(0);
                flipped = false;
            }
            else {
                changeTheme(1);
                flipped = true;
            }
        }
    }

    public void changeTheme(int t){
    }

    public static void movePlayerRight(){
        player.moveRight(currentTileMap);
    }

    public static void movePlayerLeft(){
        player.moveLeft(currentTileMap);
    }

    public static void movePlayerUp(){
        player.moveUp(currentTileMap);
    }

    public static void movePlayerDown(){
        player.moveDown(currentTileMap);
    }
}