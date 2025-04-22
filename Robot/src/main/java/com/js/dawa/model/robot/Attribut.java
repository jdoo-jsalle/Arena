package com.js.dawa.model.robot;

public class Attribut {
	
	private String mNameAttribut;
	private int mValueAttribut;
	private String mValueAttributString;
	
	public String getNameAttribut() {
		return mNameAttribut;
	}
	public void setNameAttribut(String pNameAttribut) {
		this.mNameAttribut = pNameAttribut;
	}
	public int getValueAttribut() {
		return mValueAttribut;
	}
	public void setValueAttribut(int pValueAttribut) {
		this.mValueAttribut = pValueAttribut;
	}
	public String getValueAttributString() {
		return mValueAttributString;
	}
	public void setValueAttributString(String pValueAttributString) {
		this.mValueAttributString = pValueAttributString;
	}
	
	public String toString() {
		return mNameAttribut + " str : " + mValueAttributString + " int : " + Integer.toString(mValueAttribut);
	}

}
