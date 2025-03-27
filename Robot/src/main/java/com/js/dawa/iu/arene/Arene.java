package com.js.dawa.iu.arene;


import java.util.ArrayList;
import java.util.List;

import com.js.dawa.iu.console.Console;
import com.js.dawa.robot.model.Position;

public class Arene {
	

	Console mConsole;	
	
	List<ObjetArene> mLstCaseArene = new ArrayList<ObjetArene>();
	
	List<ObjetArene> mLstObjectEphemere = new ArrayList<ObjetArene>();
	
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
	
	public List<ObjetArene> getLstCaseEphemere(){
		return mLstObjectEphemere;
	}
	
	public void setLstCaseEphemere (List<ObjetArene> pLst) {
		mLstObjectEphemere = pLst;
	}



	public AreneProps getAreneProps() {
		return mAreneProps;
	}



	public void setAreneProps(AreneProps pAreneProps) {
		this.mAreneProps = pAreneProps;
	}
	
	
	public boolean isPositionInArene (Position pPosition,int pX, int pY) {
		int lSizeArene = mAreneProps.getSize();
	
		return (pPosition.getX()+ pX <= lSizeArene &&
				pPosition.getY()+ pY <= lSizeArene  &&
				pPosition.getX()+ pX > 0 &&
				pPosition.getY()+ pY > 0);
			
	}

	


}
