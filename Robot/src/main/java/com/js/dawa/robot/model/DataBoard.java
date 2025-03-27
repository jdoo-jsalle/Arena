package com.js.dawa.robot.model;

import java.util.HashMap;
import java.util.Map;

public class DataBoard {
	
	static String BLOCK = "block";
	
	private Map<String, String> mLstData = new HashMap<>();
	
	public boolean isBlocked() {
		String lVal =mLstData.get(BLOCK);
		return lVal == null || Boolean.parseBoolean(lVal);
		
	}

	public void setBlocked(boolean isBlocked) {
		mLstData.put(BLOCK, Boolean.toString(isBlocked));
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

}
