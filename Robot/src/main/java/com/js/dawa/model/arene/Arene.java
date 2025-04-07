package com.js.dawa.model.arene;


import java.util.ArrayList;
import java.util.List;

import com.js.dawa.iu.console.Console;

public class Arene {
	

	private Console mConsole;	
	
	private List<ModuleArena> mLstModuleArene = new ArrayList<>();
	
	private List<ModuleArena> mLstModuleArenasTemp = new ArrayList<>();//Add create objet in turn.
	
	private AreneProps mAreneProps;
	
	public Arene (Console pConsole) {
		mConsole = pConsole;
	}
	

	
	
	public void addObjetArene (ModuleArena pObjetArene) {
		
		mLstModuleArenasTemp.add(pObjetArene);
	}
	
	public void updateListCase (){
		//put last objet creation in the arena
		for (ModuleArena lModule : mLstModuleArenasTemp) {
			mLstModuleArene.add(lModule);
		}
		
		mLstModuleArenasTemp.clear();
		
	}
	
	
	public List<ModuleArena> getLstCaseMain (){
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
	
	
	
	public boolean isNewPositionIsOk (ObjetArene pObjetArena, int pX, int pY) {
		int lNewX = pObjetArena.getPosition().getX() + pX;
		int lNewY = pObjetArena.getPosition().getY() + pY;
		
		
		
		int lSizeArene = mAreneProps.getSize();
		
		boolean lRes = (lNewX <= lSizeArene &&
				lNewY <= lSizeArene  &&
				lNewX > 0 &&
				lNewY > 0);//=> true, is in arena
		
		if (lRes) {
			//verify objet Present as this place
			for (ModuleArena lModule : mLstModuleArene) {
				ObjetArene lObjetTarget = lModule.getObjetArene();
			
				if (lObjetTarget != pObjetArena &&
					lObjetTarget.getPosition().compareProx(lNewX, lNewY, 0)) {
					
					boolean lTargetIsBlocking = lObjetTarget.collision(pObjetArena);
					
					if (lRes) {
						lRes = !lTargetIsBlocking;
					}
					
					pObjetArena.collision(lObjetTarget);
					
				}
				
			}
		}
		
		return lRes;
		
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
	
	public Console getConsole () {
		return mConsole;
	}

	


}
