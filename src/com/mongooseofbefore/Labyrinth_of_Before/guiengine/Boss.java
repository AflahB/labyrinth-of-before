package com.mongooseofbefore.Labyrinth_of_Before.guiengine;

import android.graphics.Bitmap;
import com.mongooseofbefore.Labyrinth_of_Before.gameengine.GameEngine;

public class Boss extends Character{
	private String path = new String();
	//the constructor
	public Boss (int x, int y, int d, Bitmap[][] bitmaps){
		super(x, y, d, bitmaps);
	}
	
	public void setBoss(int x, int y, int d){
		setXPos(x);
		setYPos(y);
		setDirection(d);
		path = findPath(0, getXPos(), getYPos());
		System.out.println("ioahwoidfwah" + path);
	}
	
	private String findPath(int d, int x, int y){
		int nLength = 200;
		int eLength = 200;
		int sLength = 200;
		int wLength = 200;		
		String nString = "";
		String eString = "";
		String sString = "";
		String wString = "";
		if( x == GameEngine.player.getXPos() && y-1 == GameEngine.player.getYPos() ) return "1";
		if( x-1 == GameEngine.player.getXPos() && y == GameEngine.player.getYPos() ) return "2";
		if( x == GameEngine.player.getXPos() && y+1 == GameEngine.player.getYPos() ) return "3";
		if( x+1 == GameEngine.player.getXPos() && y == GameEngine.player.getYPos() ) return "4";
		if(d != 1 && GameEngine.currentTileMap.getLevel()[x][y -1].getType() == 0){
			nString = findPath(1, x, y-1);
			nLength = nString.length();
		}
		if(d != 2 && GameEngine.currentTileMap.getLevel()[x +1][y].getType() == 0){
			eString = findPath(2, x+1, y);
			eLength = eString.length();
		}
		if(d != 3 && GameEngine.currentTileMap.getLevel()[x][y +1].getType() == 0){
			sString = findPath(3, x, y+1);
			sLength = sString.length();
		}
		if(d != 4 && GameEngine.currentTileMap.getLevel()[x -1][y].getType() == 0){
			wString = findPath(4, x-1, y);
			wLength = wString.length();
		}
		if(nLength < eLength && nLength < sLength && nLength < wLength){
			return nString + "1";
		}
		if(eLength < nLength && eLength < sLength && eLength < wLength){
			return eString + "2";
		}
		if(sLength < eLength && sLength < nLength && sLength < wLength){
			return wString + "3";
		}
		else{
			return nString + "4";
		}
	}
	
	public void moveBoss(){
		if(path.startsWith("1")){
			this.moveUp(GameEngine.currentTileMap);
			path = path.substring(1);
		}
		if(path.startsWith("2")){
			this.moveRight(GameEngine.currentTileMap);
			path = path.substring(1);
		}
		if(path.startsWith("3")){
			this.moveDown(GameEngine.currentTileMap);
			path = path.substring(1);
		}
		if(path.startsWith("4")){
			this.moveLeft(GameEngine.currentTileMap);
			path = path.substring(1);
		}
		else{
			path = findPath(0, getXPos(), getYPos());
		}
	}
}