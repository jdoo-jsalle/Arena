package com.js.dawa.model.arene;


import java.util.ArrayList;
import java.util.List;

import com.js.dawa.iu.console.Console;
import com.js.dawa.model.robot.Position;

public class Arene {
	

	Console mConsole;	
	
	private List<ModuleArena> mLstModuleArene = new ArrayList<>();
	
	private List<ModuleArena> mLstModuleArenasTemp = new ArrayList<>();//Add create objet in turn.
	
	private AreneProps mAreneProps;
	
	public Arene (Console pConsole) {
		mConsole = pConsole;
	}
	

	
	
	public void addObjetArene (ModuleArena pObjetArene) {
		
		mLstModuleArenasTemp.add(pObjetArene);
	}
	
	public List<ModuleArena>  getLstCase (){
		//put last objet creation in the arena
		for (ModuleArena lModule : mLstModuleArenasTemp) {
			mLstModuleArene.add(lModule);
		}
		
		mLstModuleArenasTemp.clear();
		return mLstModuleArene;
	}
	
	public void setLstCase (List<ModuleArena> pLstModuleArena) {
		mLstModuleArene = pLstModuleArena;
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
	
	public void rmDisposeObjet () {
		List<ModuleArena> lLstToDispose = new ArrayList<>();
		for (ModuleArena lModule : mLstModuleArene) {
			if (lModule.getObjetArene().isDispose()) {
				lLstToDispose.add(lModule);
			}
			
		}
		//rm
		for (ModuleArena lModule : lLstToDispose ) {
			mLstModuleArene.remove(lModule);
		}
	}

	


}
