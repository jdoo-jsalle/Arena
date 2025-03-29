package com.js.dawa.iu.arene;


import java.util.ArrayList;
import java.util.List;

import com.js.dawa.iu.console.Console;
import com.js.dawa.robot.model.Position;

public class Arene {
	

	Console mConsole;	
	
	List<ModuleArena> mLstCaseArene = new ArrayList<>();
	
	private AreneProps mAreneProps;
	
	public Arene (Console pConsole) {
		mConsole = pConsole;
	}
	

	
	public void addObjetArene (int pX, int pY,ModuleArena pObjetArene) {
		Position lPosition = new Position (pX,pY);
		pObjetArene.getObjetArene().setPosition(lPosition);
		
		//int lPos = pY * mTotY + pX;
		mLstCaseArene.add(pObjetArene);
	}
	
	public List<ModuleArena>  getLstCase (){
		return mLstCaseArene;
	}
	
	public void setLstCase (List<ModuleArena> pLstModuleArena) {
		mLstCaseArene = pLstModuleArena;
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
