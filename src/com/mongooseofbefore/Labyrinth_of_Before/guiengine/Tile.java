/*
 * File:		Tile.java - Object class for the tiles of the maze
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
package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

/**
 * Object class for tile
 * @author lpr4
 *
 */
public class Tile {
	
	private int type_;
	/**
	 * Tile constructor. Sets to type 1 (wall) by default
	 */
	public Tile(){
		type_= 1;
	}
 
	/**
	 * Returns the current tile type
	 */
	public int getType() {
		return type_;
	}

	/**
	 * Sets the current tile type
	 * @param type
	 */
	public void setType(int type) {
		type_ = type;
	}
	
}
