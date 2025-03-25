package com.js.dawa.iu.arene;

import java.util.HashMap;
import java.util.Map;

import com.js.dawa.robot.model.Attribut;

public class RobotsProps {
	
	static String PDV = "PDV";
	static String NAME ="NAME";
	static String COLOR= "COLOR";
	
	
	
	private Map<String, Attribut> mLstAttributs = new HashMap<>(3);
	
	
	public void setLstAttribut (Map<String, Attribut> pLstAttributs) {
		mLstAttributs = pLstAttributs;
	}
	
	public Map<String, Attribut> getLstAttribut(){
		return mLstAttributs;
	}
	
	public int getPdV () {
		return mLstAttributs.get(PDV).getValueAttribut();
	}
	
	public String getNom () {
		return mLstAttributs.get(NAME).getValueAttributString();
	}
	
	
	public String getColor() {
		return mLstAttributs.get(COLOR).getValueAttributString();
		
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
	

}
