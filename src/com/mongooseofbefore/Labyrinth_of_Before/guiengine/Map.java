package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Bitmap;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;

public class Map {
	private Tile[][]	tileMap_;
	private int			width;
	private int			height;
	private Bitmap      bitmap0;
	private Bitmap      bitmap1;
	/**
	 * initialises the map Instance 
	 * @param xw the map width in tiles
	 * @param yh the map height in tiles
	 */
	public Map(int xw, int yh) {
		tileMap_    = new Tile[xw][yh];
		this.width  = xw - 1;
		this.height = yh - 1;
	}

	/**
	 *  Creates a new tile in every space in the array, then sets the outer tiles to wall tiles
	 */
	public void init(Context context) {

		bitmap0 = Helper.getBitmapFromAsset("art/menu/bgn.png", context);
		bitmap1 = Helper.getBitmapFromAsset("art/menu/bgn.png", context);
		GameEngine.flipped = false;
	}

	/**
	 * gets the current Tile Map
	 * @return
	 */
	public Tile[][] getLevel() {
		return tileMap_;
	}
	/**
	 * sets the level
	 * @param tileMap an array containing the new map
	 */
	public void setLevel(Tile[][] tileMap) {
		this.tileMap_ = tileMap;
	}

	/**
	 * draws the frame
	 * 
	 * @param canvas the canvas to draw on
	 */
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
					try {
						canvas.drawBitmap(bitmap1, null, rect, null);
					} catch (Exception e) {
					}
				} else {
					try {
						canvas.drawBitmap(bitmap0, null, rect, null);
					} catch (Exception e) {
					}
				}

				w++;
			}
			w = 0;
			h++;
		}
	}
}
