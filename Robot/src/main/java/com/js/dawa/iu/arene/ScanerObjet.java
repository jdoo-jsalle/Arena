package com.js.dawa.iu.arene;

import java.util.ArrayList;
import java.util.List;

import com.js.dawa.robot.model.Position;

public class ScanerObjet {

	Arene mArene;
	
	
	void init (Arene pArene) {
		mArene = pArene;
	}
	
	
	List<ObjetArene> detectObjet (ObjetArene pObjetArene, int pRayon){
		
		List<ObjetArene> lRes = new ArrayList<>();
		
		Position Pos = pObjetArene.getPosition();
		
		for (ModuleArena lModule : mArene.getLstCase()) {
			
		}
		
		return lRes;
		
	}
	
	
}
