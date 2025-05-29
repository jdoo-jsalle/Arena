package com.js.dawa.model.position;



import com.js.dawa.util.DawaRunTimeException;

public class Axe {
	
	
	
	private int mDir =0;
	private int mTotAngle=8;
	
	
	
	public Axe (int pTotAngle) {
		setTotAngle(pTotAngle);
	}
	
	public Axe () {
		//na
	}



	public int getTotAngle() {
		return mTotAngle;
	}



	public void setTotAngle(int pTotAngle) {
		this.mTotAngle = pTotAngle;
		if (mTotAngle == 0) {
			throw new DawaRunTimeException("tot angle must be > 0");
		}
			
	}
	
	public String toString () {
		return Integer.toString(mDir);
	}



	public int getDir() {
		return mDir;
	}



	public void setDir(int pDir) {
		this.mDir = pDir;
		verifyDir();
	}
	
	
	void verifyDir () {
		mDir = mDir % mTotAngle;
		if (mDir < 0){
			mDir = mTotAngle + mDir;
		}
	}
	
	public void doRotation (int pVal) {
		setDir (mDir + pVal);
		
	
	}
	
	double convertDegree () {
		return  mDir == 0 ? 360d : mDir * 360d / mTotAngle;
	}
	
	public Position getVector () {
		
		double lAngle = Math.toRadians(convertDegree());
		double lx = Math.cos(lAngle);
		double ly = Math.sin(lAngle);
		return new Position (lx,ly);
	}
	
	public void rotLeft () {
		doRotation(-1);
	}
	
	public void rotRight () {
		doRotation(1);
	}
	
	
	
	

}
