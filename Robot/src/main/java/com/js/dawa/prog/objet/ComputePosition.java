package com.js.dawa.prog.objet;

import java.util.ArrayList;
import java.util.List;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.ObjetArene;
import com.js.dawa.robot.model.Position;

public class ComputePosition {
	
	List<ObjetArene> mObjetArene = new ArrayList<>();
	
	Arene mArene;
	
	public ComputePosition (Arene pArene) {
		mArene = pArene;
	}
	
	public void addObjet (ObjetArene pObjetArene) {
		mObjetArene.add(pObjetArene);
	}
	
	public void computeNewPosition() {
		List<ObjetArene> lLet = new ArrayList<>();
		for (ObjetArene pObjet : mObjetArene) {
			Position lPosition = pObjet.getPosition();
			//TODO affect a algo compute position by objet
			if (mArene.isPositionInArene(lPosition, 1, 1)) {
				lPosition.addXY(1, 1);
				
			}
			else {
				lLet.add(pObjet);//for rm it
			}
			
		}
		
		//Rm
		for (ObjetArene lObjetArene : lLet) {
			mObjetArene.remove(lObjetArene);
		}
		
		
		
	}
	
	public List<ObjetArene> getLstObjet (){
		return mObjetArene;
	}

}
