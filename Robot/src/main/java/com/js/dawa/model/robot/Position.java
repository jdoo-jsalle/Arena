package com.js.dawa.model.robot;

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
	
	public boolean compareProx (int pX, int pY, int pRayon) {
		return Math.abs(mX-pX) <= pRayon &&
				       Math.abs(mY-pY) <= pRayon;
			
	}
	
	public String toString() {
		return "X : " + Integer.toString(mX)  + " Y : " + Integer.toString(mY);
	}
	
	public Position getVector (Position pPosition) {
		int lVx = pPosition.getX()-mX;
		int lVy = pPosition.getY()-mY;
		
		lVx = lVx == 0 ? 0 : (Math.abs(lVx)/lVx);
		lVy = lVy == 0 ? 0 : (Math.abs(lVy)/lVy);
		
		return new Position(lVx,lVy);
		
	}
	
	public double distance (Position pPosition) {
		int lDisX = mX - pPosition.getX();
		int lDisY = mY - pPosition.getY();
		
		//*1D for cast in to double
		return Math.sqrt(lDisX * lDisX * 1D + lDisY * lDisY * 1D);
	}

}
