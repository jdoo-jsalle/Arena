package com.js.dawa.model.position;

public class Position {
	
	double mX;
	double mY;
	
	public Position (int pX, int pY) {
		setXY(pX,pY);
	}
	
	public Position (double pX, double pY) {
		setXY(pX,pY);
	}
	
	public void setXY(double pX, double pY) {
		mX = pX;
		mY = pY;
	}
	
	public double getX() {
		return mX;
	}
	
	public double getY() {
		return mY;
	}
	
	public int getXi() {
		return (int)getX();
	}
	
	public int getYi() {
		return (int)getY();
	}
	
	public void addX (double pX) {
		mX += pX;
	}
	
	public void addY (double pY) {
		mY += pY;
	}
	
	public void addXY (double pX, double pY) {
		addX(pX);
		addY(pY);
	}
	
	public boolean compareProx (double pX, double pY, double pRayon) {
		return Math.abs(mX-pX) <= pRayon &&
				       Math.abs(mY-pY) <= pRayon;
			
	}
	
	public String toString() {
		return "X : " + Double.toString(mX)  + " Y : " + Double.toString(mY);
	}
	
	public Position getVector (Position pPosition) {
		double lVx = pPosition.getX()-mX;
		double lVy = pPosition.getY()-mY;
		
		lVx = lVx == 0 ? 0 : (Math.abs(lVx)/lVx);
		lVy = lVy == 0 ? 0 : (Math.abs(lVy)/lVy);
		
		return new Position(lVx,lVy);
		
	}
	
	public double distance (Position pPosition) {
		double lDisX = mX - pPosition.getX();
		double lDisY = mY - pPosition.getY();
		
		//*1D for cast in to double
		return Math.sqrt(lDisX * lDisX * 1D + lDisY * lDisY * 1D);
	}

}
