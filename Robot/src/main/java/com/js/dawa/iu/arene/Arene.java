package com.js.dawa.iu.arene;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.js.dawa.iu.console.Console;
import com.js.dawa.robot.model.Position;

public class Arene {
	
	int mTotCoteCase = 4;
	int mTotX = 100;
	int mTotY = 100;
	Console mConsole;	
	
	List<ObjetArene> mLstCaseArene = new ArrayList<ObjetArene>();
	
	public Arene (Console pConsole) {
		mConsole = pConsole;
	}
	

	
	public void addObjetArene (int pX, int pY,ObjetArene pObjetArene) {
		Position lPosition = new Position (pX,pY);
		pObjetArene.setPosition(lPosition);
		
		//int lPos = pY * mTotY + pX;
		mLstCaseArene.add(pObjetArene);
	}
	
	public List<ObjetArene>  getLstCase (){
		return mLstCaseArene;
	}
	

	


}
