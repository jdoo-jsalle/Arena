package com.js.dawa.model.arene;

import java.util.ArrayList;
import java.util.List;

import com.js.dawa.model.robot.Position;

public class ScanerObjet {

	Arene mArene;
	
	
	void init (Arene pArene) {
		mArene = pArene;
	}
	
	
	List<ObjetArene> detectObjet (ObjetArene pObjetArene, int pRayon){
		
		List<ObjetArene> lRes = new ArrayList<>();
		
		Position lPos1 = pObjetArene.getPosition();
		
		for (ModuleArena lModule : mArene.getLstCase()) {
			Position lPos2 = lModule.getObjetArene().getPosition();
			if (compareProx(lPos1, lPos2, pRayon) && lModule.getObjetArene().isVisible()) {
				lRes.add(lModule.getObjetArene());
				
			}
		}
		
		return lRes;
		
	}
	
	
	boolean compareProx (Position p1, Position p2, int pRayon) {
		return Math.abs(p1.getX()-p2.getX()) <= pRayon &&
				       Math.abs(p1.getY()-p2.getY()) <= pRayon;
			
	}
	
	
	
}
