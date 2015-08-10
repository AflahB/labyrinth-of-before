package com.mongooseofbefore.labyrinthofbefore.gameengine;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Parcel;
import android.os.Parcelable;

import com.mongooseofbefore.labyrinthofbefore.guiengine.Camera;
import com.mongooseofbefore.labyrinthofbefore.guiengine.Helper;
import com.mongooseofbefore.labyrinthofbefore.guiengine.Level;
import com.mongooseofbefore.labyrinthofbefore.guiengine.Map;

public class GameEngine implements Parcelable{

    private static Level level_;
    private static Camera camera_;

    Bitmap[]        currentBitmaps;
    Bitmap[]        flipBitmaps;
    Bitmap[][]      playerSprites;
    Bitmap[][]      bossSprites;

    private int     levelCount_;

    private  Context context_;

    public GameEngine(){}

    protected GameEngine(Parcel in) {
        currentBitmaps = in.createTypedArray(Bitmap.CREATOR);
        flipBitmaps = in.createTypedArray(Bitmap.CREATOR);
        levelCount_ = in.readInt();
    }

    public static final Creator<GameEngine> CREATOR = new Creator<GameEngine>() {
        @Override
        public GameEngine createFromParcel(Parcel in) {
            return new GameEngine(in);
        }

        @Override
        public GameEngine[] newArray(int size) {
            return new GameEngine[size];
        }
    };

    /**
     * Initializes the Game Engine, Creating the two Map Objects
     * @param context Context of the application
     */
    public void init(Context context) {
        levelCount_ = 1;

        context_    = context;

        currentBitmaps  = new Bitmap[2];

        flipBitmaps     = new Bitmap[2];

        playerSprites   = new Bitmap[4][4];
        playerSprites[0][0]    = Helper.getBitmapFromAsset("art/character/North Facing/characterN-1.png", context_);
        playerSprites[0][1]    = Helper.getBitmapFromAsset("art/character/North Facing/characterN-2.png", context_);
        playerSprites[0][2]    = Helper.getBitmapFromAsset("art/character/North Facing/characterN-3.png", context_);
        playerSprites[0][3]    = Helper.getBitmapFromAsset("art/character/North Facing/characterN-4.png", context_);
        playerSprites[1][0]    = Helper.getBitmapFromAsset("art/character/East Facing/characterE-1.png", context_);
        playerSprites[1][1]    = Helper.getBitmapFromAsset("art/character/East Facing/characterE-2.png", context_);
        playerSprites[1][2]    = Helper.getBitmapFromAsset("art/character/East Facing/characterE-3.png", context_);
        playerSprites[1][3]    = Helper.getBitmapFromAsset("art/character/East Facing/characterE-4.png", context_);
        playerSprites[2][0]    = Helper.getBitmapFromAsset("art/character/South Facing/characterS-1.png", context_);
        playerSprites[2][1]    = Helper.getBitmapFromAsset("art/character/South Facing/characterS-2.png", context_);
        playerSprites[2][2]    = Helper.getBitmapFromAsset("art/character/South Facing/characterS-3.png", context_);
        playerSprites[2][3]    = Helper.getBitmapFromAsset("art/character/South Facing/characterS-4.png", context_);
        playerSprites[3][0]    = Helper.getBitmapFromAsset("art/character/West Facing/characterW-1.png", context_);
        playerSprites[3][1]    = Helper.getBitmapFromAsset("art/character/West Facing/characterW-2.png", context_);
        playerSprites[3][2]    = Helper.getBitmapFromAsset("art/character/West Facing/characterW-3.png", context_);
        playerSprites[3][3]    = Helper.getBitmapFromAsset("art/character/West Facing/characterW-4.png", context_);

        bossSprites     = new Bitmap[4][4];

        //Loads current level from CSV
        load(levelCount_);

        camera_= new Camera();
    }

    public Level getLevel(){ return level_;}

    public void load(int level){
        String  path;

        //Selects level data based on current level value
        switch(level){
            case 1:     path    = "levels/fields.csv";
                currentBitmaps[0] = Helper.getBitmapFromAsset("art/tiles/fields/floor 1.png", context_);
                currentBitmaps[1] = Helper.getBitmapFromAsset("art/tiles/fields/wall 1.png", context_);
                flipBitmaps[0]  = Helper.getBitmapFromAsset("art/tiles/fields/floor 2.png", context_);
                flipBitmaps[1]  = Helper.getBitmapFromAsset("art/tiles/fields/wall 2.png", context_);
                break;
            case 2:     path    = "levels/hadespalace.csv";
                currentBitmaps[0] = Helper.getBitmapFromAsset("art/tiles/Hades palace/floor 1.png", context_);
                currentBitmaps[1] = Helper.getBitmapFromAsset("art/tiles/Hades palace/wall 1.png", context_);
                flipBitmaps[0]  = Helper.getBitmapFromAsset("art/tiles/Hades palace/floor 2.png", context_);
                flipBitmaps[1]  = Helper.getBitmapFromAsset("art/tiles/Hades palace/wall 2.png", context_);
                break;
            default:     path   = "levels/debug.csv";
                currentBitmaps[0] = Helper.getBitmapFromAsset("art/tiles/tartarus/floor 1.png", context_);
                currentBitmaps[1] = Helper.getBitmapFromAsset("art/tiles/tartarus/wall 1.png", context_);
                flipBitmaps[0]  = Helper.getBitmapFromAsset("art/tiles/tartarus/floor 2.png", context_);
                flipBitmaps[1]  = Helper.getBitmapFromAsset("art/tiles/tartarus/wall 2.png", context_);
                break;
        }

        Object[] levelObjects = Helper.parseLevelCSV(path, context_);
        level_ = new Level(levelObjects, currentBitmaps, flipBitmaps, playerSprites, bossSprites);
    }

    public void nextLevel(){
        if(levelCount_ > 3)
            endGame();
        levelCount_++;
        load(levelCount_);
    }

    public void endGame(){
    }


    public void update(){
        if(level_.getPlayer().nextLevel()) {
            nextLevel();
            level_.getPlayer().resetNextLevel();
        }
    }

    /**
     * Draws the map on the canvas along with the player and also the boss
     * @param canvas The canvas object to draw the map on.
     */
    public void drawMap(Canvas canvas) {
        camera_.draw(level_, canvas);
    }

    public void mapFlip(){
        int playerX = level_.getPlayer().getXPos();
        int playerY = level_.getPlayer().getYPos();

        if(level_.flip.getTileMap()[playerX][playerY].getType() == 0){
            Map temp_       = level_.current;	//Backs up the current level so that changes can be made to it
            level_.current  = level_.flip;		//Sets the current level to the alternate level
            level_.flip     = temp_;	//Restores the previous level to the the alternate level
        }
    }

    public static void movePlayer(int direction){
        if(direction < 0 || direction > 3)
            throw new Error("Invalid Move Direction");
        level_.getPlayer().move(level_.current, direction);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedArray(currentBitmaps, flags);
        dest.writeTypedArray(flipBitmaps, flags);
        dest.writeInt(levelCount_);
    }
}