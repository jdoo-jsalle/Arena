package com.js.dawa.model.arene;

public class Energie {

	
	private int mTot;
	private int mSpend;
	private int mDamage;
	
	private int mMax = Integer.MAX_VALUE;
	private int mMin = 0;
	
	public Energie () {
		//na
	}
	
	public Energie (int pVal) {
		mTot = pVal;
	}
	

	public int getTot() {
		return mTot;
	}

	public void setTot(int pTot) {
		this.mTot = pTot;
		
		verify();
		
	}
	
	
	public void add (int pEnergie) {
		mTot = mTot + pEnergie;
		if (pEnergie < 0) mSpend = mSpend - pEnergie;
		verify();
		
	}
	
	public void addDamage (int pEnergie) {
		mDamage = mDamage + pEnergie;
		add(-pEnergie);
	}
	
	public double averageSpendByTurn (int pTotTurn) {
		return pTotTurn != 0 ? mSpend/pTotTurn : 0;
	}
	
	public double averageDamageByTurn (int pTotTurn) {
		return pTotTurn != 0 ? mDamage/pTotTurn : 0;
	}
	
	public boolean isEmpty () {
		return mTot <=0;
		
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
	
	@Override
	public String toString() {
		return Integer.toString(mTot);
	}
	
	
}
