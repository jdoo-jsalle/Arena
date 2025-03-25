package com.js.dawa.iu.arene;


import java.util.ArrayList;
import java.util.List;

import com.js.dawa.iu.console.Console;
import com.js.dawa.robot.model.Position;

public class Arene {
	

	Console mConsole;	
	
	List<ObjetArene> mLstCaseArene = new ArrayList<ObjetArene>();
	
	private AreneProps mAreneProps;
	
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



	public AreneProps getAreneProps() {
		return mAreneProps;
	}



	public void setAreneProps(AreneProps pAreneProps) {
		this.mAreneProps = pAreneProps;
	}
	
	
	

	


}
