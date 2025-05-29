package com.js.dawa.model.arene;

import java.util.HashMap;
import java.util.Map;

import com.js.dawa.model.robot.Attribut;

public class AreneProps {
	
	static String SIZE = "SIZE";
	static String TITLE ="TITLE";
	static String HEDGE_SIZE ="HEDGE_SIZE";
	
	
	
	private Map<String, Attribut> mLstAttributs = new HashMap<>(2);
	
	
	public void setLstAttribut (Map<String, Attribut> pLstAttributs) {
		mLstAttributs = pLstAttributs;
	}
	
	public Map<String, Attribut> getLstAttribut(){
		return mLstAttributs;
	}
	
	public int getSize () {
		return mLstAttributs.get(SIZE).getValueAttribut();
	}

	public String getTitle () {
		return mLstAttributs.get(TITLE).getValueAttributString();
	}
	
	public int getHedgeSize () {
		return mLstAttributs.get(HEDGE_SIZE).getValueAttribut();
	}
	
	public void setSize (int pSize) {
		Attribut lAttribut = new Attribut();
		lAttribut.setValueAttribut(pSize);
		mLstAttributs.put (SIZE,lAttribut);
	}
	
	public void setTitle (String pTitle) {
		Attribut lAttribut = new Attribut();
		lAttribut.setValueAttributString(pTitle);
		mLstAttributs.put (TITLE,lAttribut);
		
	}
	
	public void setHedgeSide (int pHedgeSize) {
		Attribut lAttribut = new Attribut();
		lAttribut.setValueAttribut(pHedgeSize);
		mLstAttributs.put (HEDGE_SIZE,lAttribut);
	}
}