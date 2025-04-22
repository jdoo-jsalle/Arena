package com.js.dawa.model.arene;


public class HurtObject {
	
	static String HIT = "hit";
	
	private int mHit=0;
	
	ObjetArene mObjetArene;
	
	public HurtObject(int pVal) {
		mHit = pVal;
	}
	
	
	void init (ObjetArene pObjetArene) {
		mObjetArene = pObjetArene;
	}
	
	public void setHit (int pValue) {
		mHit = pValue;
	}
	
	int getHit () {
		return mHit;
	}
	
	
	public void collision (ObjetArene pObjetArene) {
		Energie lEnergie = pObjetArene.getEnergie();
		if (lEnergie != null)
		   pObjetArene.getEnergie().addDamage(mHit);
		
	}

}
