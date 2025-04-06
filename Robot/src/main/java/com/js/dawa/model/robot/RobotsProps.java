package com.js.dawa.model.robot;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class RobotsProps {
	
	private static final Logger LOGGER =  LogManager.getLogger( RobotsProps.class );
	
	static String PDV = "PDV";
	static String NAME ="NAME";
	static String COLOR= "COLOR";
	static String VISIBILITE ="visibilite";
	
	
	
	private Map<String, Attribut> mLstAttributs = new HashMap<>(3);
	
	
	public void setLstAttribut (Map<String, Attribut> pLstAttributs) {
		mLstAttributs = pLstAttributs;
	}
	
	
	
	
	public Map<String, Attribut> getLstAttribut(){
		return mLstAttributs;
	}
	
	
	int getIntVal (String pKey) {
		Attribut lAttr = mLstAttributs.get(pKey);
		int lRes = 0;
		if (lAttr != null) {
			lRes = lAttr.getValueAttribut();
		}
		return lRes;
	}
	
	String getStringVal (String pKey) {
		Attribut lAttr = mLstAttributs.get(pKey);
		String lRes = "";
		if (lAttr != null) {
			lRes = lAttr.getValueAttributString();
		}
		return lRes;
	}
	
	boolean getBooleanVal (String pKey, boolean pDefaultValue) {
		Attribut lAttr = mLstAttributs.get(pKey);
		boolean lRes = pDefaultValue;
		if (lAttr != null) {
			String lVal = lAttr.getValueAttributString();
			lRes = Boolean.parseBoolean(lVal);
			LOGGER.debug("Val \"{}\" => res \"{}\"",lVal, lRes);
		}
		return lRes;
	}
	
	public int getPdV () {
		return getIntVal(PDV);
	}
	
	public String getNom () {
		return getStringVal(NAME);
	}
	
	
	public String getColor() {
		return getStringVal(COLOR);
		
	}
	
	public boolean isVisibilite () {
		return getBooleanVal(VISIBILITE,true);
	}
	
	public void setColor (String pColor) {
		Attribut lAttribut = new Attribut();
		lAttribut.setNameAttribut(COLOR);
		lAttribut.setValueAttributString(pColor);
		mLstAttributs.put(COLOR, lAttribut);
	}
	
	public void setPdv (int pPdv) {
		Attribut lAttribut = new Attribut();
		lAttribut.setNameAttribut(PDV);
		lAttribut.setValueAttribut(pPdv);
		mLstAttributs.put(PDV, lAttribut);
	}
	
	public void setName (String pNom) {
		Attribut lAttribut = new Attribut();
		lAttribut.setNameAttribut(NAME);
		lAttribut.setValueAttributString(pNom);
		mLstAttributs.put(NAME, lAttribut);
	}
	
	
	
	public void setVisibilte (boolean pVisibilite) {
		Attribut lAttribut = new Attribut();
		lAttribut.setNameAttribut(VISIBILITE);
		lAttribut.setValueAttributString(Boolean.toString(pVisibilite));
		LOGGER.debug("Val boolean : {}",lAttribut.getValueAttributString());
		mLstAttributs.put(VISIBILITE, lAttribut);
	}
	
	
	public String toString() {
		return mLstAttributs.toString();
	}
	

}
