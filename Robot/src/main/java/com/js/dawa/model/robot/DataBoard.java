package com.js.dawa.model.robot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.js.dawa.model.arene.ObjetArene;

public class DataBoard {
	
	static String BLOCK = "block";
	
	static String DETECTED = "detected";

	
	private Map<String, String> mLstData = new HashMap<>();
	
	private List<ObjetArene> mLstObjetArene;
	
	public boolean isBlocked() {
		String lVal =mLstData.get(BLOCK);
		return getBooleanValue(lVal);
		
	}

	
	public void setBlocked(boolean isBlocked) {
		mLstData.put(BLOCK, Boolean.toString(isBlocked));
	}
	
	public void setRobotsAreDetected (boolean isDetected) {
		mLstData.put(DETECTED, Boolean.toString(isDetected));
	}
	
	public String getVariable (String pKey) {
		return mLstData.get(pKey);
	}
	
	public void setVariable (String pKey, String pVal) {
		mLstData.put(pKey, pVal);
	}
	
	public Map<String, String> getLstVar(){
		return mLstData;
	}
	
	boolean getBooleanValue (String pVal) {
		return pVal == null || Boolean.parseBoolean(pVal);
	}
	
	public void setListObjetDetected (List<ObjetArene> pLstObjetArene) {
		mLstObjetArene = pLstObjetArene;
		setRobotsAreDetected(mLstObjetArene != null && !mLstObjetArene.isEmpty());
		
	}
	
	public ObjetArene getFirstRobotInLstObjetDetected () {
		
		ObjetArene lRes = null;
		if (mLstObjetArene != null) {
			for (ObjetArene lO : mLstObjetArene) {
				if (lO instanceof Robot) {
					lRes = lO;
				}
			}
		}
		
		return lRes;
		
	}
	
	

}
