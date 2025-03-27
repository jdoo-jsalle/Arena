package com.js.dawa.robot.model;

public class Position {
	
	int mX;
	int mY;
	
	public Position (int pX, int pY) {
		mX = pX;
		mY = pY;
	}
	
	public int getX() {
		return mX;
	}
	
	public int getY() {
		return mY;
	}
	
	public void addX (int pX) {
		mX += pX;
	}
	
	public void addY (int pY) {
		mY += pY;
	}
	
	public void addXY (int pX, int pY) {
		addX(pX);
		addY(pY);
	}

}
