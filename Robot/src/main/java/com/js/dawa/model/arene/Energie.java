package com.js.dawa.model.arene;

public class Energie {

	
	private int mTot;
	
	private int mMax = Integer.MAX_VALUE;
	private int mMin = 0;
	
	
	

	public int getTot() {
		return mTot;
	}

	public void setTot(int pTot) {
		this.mTot = pTot;
		verify();
		
	}
	
	
	public void add (int pEnergie) {
		mTot = mTot + pEnergie;
		verify();
		
	}
	
	public boolean isEmpty () {
		return mTot <0;
		
	}
	
	public void purgeAllEnergie () {
		mTot =0;
		verify();
	}

	public int getMax() {
		return mMax;
	}

	public void setMax(int mMax) {
		this.mMax = mMax;
		verify();
	}

	public int getMin() {
		return mMin;
	}

	public void setMin(int mMin) {
		this.mMin = mMin;
	}
	
	
	void verify () {
		if (mTot > mMax) {
			mTot = mMax;
		}
		if (mTot < mMin) {
			mTot = mMin;
		}
	}
	
	
}
