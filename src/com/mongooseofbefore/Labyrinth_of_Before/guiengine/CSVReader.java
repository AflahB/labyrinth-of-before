/*
 *  File:       Reader.java - A basic file reader
 *
 *  OD:         Aflah
 *  License:    It's all good homie.
 *  Issues:     This only reads in a file and stores it into a list. There is no implementation for it to create any game object as of yet.
 *  			The files need to be hard-coded in as there is no file dialogue implementation that allows users to select their own files.
 */
package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;


import android.content.Context;
import android.widget.Toast;

public class CSVReader{

	public static void loadLevel(String path, Map current, Map flip, Context context) {

		Tile[][] level = new Tile[21][21];
		Tile[][] level2 = new Tile[21][21];
		System.out.println("run");
		String line;

		BufferedReader csvReader;
		System.out.println("csvReader");
		try {
			// Creates a new input stream from the assets folder to the
			// specified file
			// in this case csv.csv
			InputStream inputStream_ = context.getResources().getAssets()
					.open(path);

			csvReader = new BufferedReader(new InputStreamReader(inputStream_));
			line = csvReader.readLine();

			while (line != null) {
				line = csvReader.readLine();
				
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

				String playerPosition[] = line.split(",");
                if(playerPosition.length == 3) {
                    int pX = Integer.parseInt(playerPosition[0]);
                    int pY = Integer.parseInt(playerPosition[1]);
                    int pD = Integer.parseInt(playerPosition[2]);
                    GameEngine.player.setPlayer(pX, pY, pD);
                }
                else {
                    throw new IOException();
                }

				line = csvReader.readLine();

				String bossArray[] = line.split(",");
				if(bossArray.length == 3){
					int bX = Integer.parseInt(bossArray[0]);
					int bY = Integer.parseInt(bossArray[1]);
					int bD = Integer.parseInt(bossArray[2]);
					GameEngine.boss.setBoss(bX, bY, bD);
					line = csvReader.readLine();
				}
				
				x = 0;
				y = 0;
				
				while (y <= 20) {
					String lineArray[] = line.split(",");
					while (x <= 20) {
						Tile newTile = new Tile();
						level2[x][y] = newTile; // fills the array
						t = Integer.parseInt(lineArray[x]);
						level2[x][y].setType(t);
						x++;
					}
					x = 0;
					y++;
					line = csvReader.readLine();
				}
			}

			current.setLevel(level);
			flip.setLevel(level2);
			csvReader.close();


		} catch (IOException e) {
			Toast.makeText(context, "Error Reading File \n" + e.toString(),
					Toast.LENGTH_SHORT).show();
		}

	}	

}

