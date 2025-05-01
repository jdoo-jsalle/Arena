package com.js.dawa.model.arene;


import java.util.ArrayList;
import java.util.List;

import com.js.dawa.iu.console.Console;

public class Arene {
	

	private Console mConsole;	
	

	
	private List<ModuleArena> mLstModuleArenasTemp = new ArrayList<>();//Add create objet in turn.
	
	private AreneProps mAreneProps;
	
	AreneLstObjet mAreneLstObject= new AreneLstObjet();
	
	public Arene (Console pConsole) {
		mConsole = pConsole;
	}
	

	
	
	public void addObjetArene (ModuleArena pObjetArene) {
		
		mLstModuleArenasTemp.add(pObjetArene);
	}
	
	public void updateListCase (){
		synchronized (mAreneLstObject.getLstCaseMain()) {
			//put last objet creation in the arena
			for (ModuleArena lModule : mLstModuleArenasTemp) {
				mAreneLstObject.getLstCaseMain().add(lModule);
			}
		}
	
		
		mLstModuleArenasTemp.clear();
		
	}
	
	
	public List<ModuleArena> getLstCaseMain (){
		return mAreneLstObject.getLstCaseMain();
	}
	
	public void setLstCase (List<ModuleArena> pLstModuleArena) {
		mAreneLstObject.setLstCase(pLstModuleArena);
	}
	
	



	public AreneProps getAreneProps() {
		return mAreneProps;
	}



	public void setAreneProps(AreneProps pAreneProps) {
		this.mAreneProps = pAreneProps;
	}
	
	
	
	public boolean isNewPositionIsOk (ObjetArene pObjetArena, double pX, double pY) {
		double lNewX = pObjetArena.getPosition().getX() + pX;
		double lNewY = pObjetArena.getPosition().getY() + pY; 
		
		
		
		int lSizeArene = mAreneProps.getSize();
		
		boolean lRes = (lNewX <= lSizeArene &&
				lNewY <= lSizeArene  &&
				lNewX > 0 &&
				lNewY > 0);//=> true, is in arena
		
		if (lRes) {
			//verify objet Present as this place
			for (ModuleArena lModule : mAreneLstObject.getLstCaseMain()) {
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
		for (ModuleArena lModule : mAreneLstObject.getLstCaseMain()) {
			if (lModule.getObjetArene().isDispose()) {
				lLstToDispose.add(lModule);
			}
			
		}
		//rm
		for (ModuleArena lModule : lLstToDispose ) {
			mAreneLstObject.getLstCaseMain().remove(lModule);
		}
	}
	
	public Console getConsole () {
		return mConsole;
	}

	


}
