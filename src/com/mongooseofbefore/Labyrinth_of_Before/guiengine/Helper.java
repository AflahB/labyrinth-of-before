package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Helper {

    public static Bitmap getBitmapFromAsset(String strName, Context context)
    {
        try {
            AssetManager assetManager = context.getAssets();
            InputStream inputStream = assetManager.open(strName);
            return BitmapFactory.decodeStream(inputStream);
        }
        catch (IOException e){
            Toast.makeText(context, "Error in filepath", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

        return null;
    }

    public static Object[] parseLevelCSV(String path, Context context) {

        Object[] readObjects = new Object[4];
        Tile[][] level = new Tile[21][21];
        Tile[][] flipLevel = new Tile[21][21];
        String line;

        BufferedReader csvReader;
        try {
            // Creates a new input stream from the assets folder to the
            // specified file
            // in this case csv.csv
            InputStream inputStream_ = context.getResources().getAssets()
                    .open(path);

            csvReader = new BufferedReader(new InputStreamReader(inputStream_));
            line = csvReader.readLine();

            while (line != null) {

                int y = 0;
                int x = 0;
                int t;
                while (y <= 20) {
                    String lineArray[] = line.split(",");
                    while (x <= 20) {
                        Tile newTile = new Tile();
                        level[x][y] = newTile; // fills the array
                        t = Integer.parseInt(lineArray[x]);
                        level[x][y].setType(t);
                        x++;
                    }
                    x = 0;
                    y++;
                    line = csvReader.readLine();
                }
                readObjects[0] = level;

                String[] playerLine = line.split(",");
                if(playerLine.length == 3) {
                    int[] playerPosition = new int[3];
                    for(int i = 0; i < playerLine.length; i++)
                        playerPosition[i] = Integer.parseInt(playerLine[i]);
                    readObjects[2] = playerPosition;
                }
                else {
                    throw new IOException();
                }

                line = csvReader.readLine();

                String bossPosition[]  = line.split(",");
                if(bossPosition.length == 3){
                    readObjects[3]  = bossPosition;
                    line            = csvReader.readLine();
                }

                x = 0;
                y = 0;

                while (y <= 20) {
                    String lineArray[] = line.split(",");
                    while (x <= 20) {
                        Tile newTile    = new Tile();
                        flipLevel[x][y] = newTile; // fills the array
                        t               = Integer.parseInt(lineArray[x]);
                        flipLevel[x][y].setType(t);
                        x++;
                    }
                    x       = 0;
                    y++;
                    line    = csvReader.readLine();
                }
                readObjects[1] = flipLevel;
            }
            csvReader.close();
            return readObjects;


        } catch (IOException e) {
            Toast.makeText(context, "Error Reading File \n" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
        return null;
    }
}
