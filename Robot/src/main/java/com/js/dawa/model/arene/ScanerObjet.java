package com.js.dawa.model.arene;

import java.util.ArrayList;
import java.util.List;

import com.js.dawa.model.robot.Position;

public class ScanerObjet {

	Arene mArene;
	
	
	public void init (Arene pArene) {
		mArene = pArene;
	}
	
	
	public List<ObjetArene> detectObjet (ObjetArene pObjetArene, int pRayon){
		
		List<ObjetArene> lRes = new ArrayList<>();
		
		Position lPos1 = pObjetArene.getPosition();
		
		for (ModuleArena lModule : mArene.getLstCase()) {
			
			ObjetArene lObjetTarget = lModule.getObjetArene();
			if (lObjetTarget != pObjetArene) {
				Position lPos2 = lObjetTarget.getPosition();
				if (compareProx(lPos1, lPos2, pRayon) && lObjetTarget.isVisible()) {
					lRes.add(lObjetTarget);//add object detected
					
				}
			}
		}
		
		return lRes;
		
	}
	
	
	boolean compareProx (Position p1, Position p2, int pRayon) {
		return Math.abs(p1.getX()-p2.getX()) <= pRayon &&
				       Math.abs(p1.getY()-p2.getY()) <= pRayon;
			
	}
	
	
	
}
