package com.js.dawa.model.arene;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.model.robot.Position;

public class ScanerObjet {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( ScanerObjet.class );

	Arene mArene;
	
	
	public void init (Arene pArene) {
		mArene = pArene;
	}
	
	
	public List<ObjetArene> detectObjet (ObjetArene pObjetArene, int pRayon, boolean pOnlyRobot){
		
		List<ObjetArene> lRes = new ArrayList<>();
		
		Position lPos1 = pObjetArene.getPosition();
		
		for (ModuleArena lModule : mArene.getLstCaseMain()) {
			if (lModule.isRobot() || !pOnlyRobot) {
				ObjetArene lObjetTarget = lModule.getObjetArene();
				if (lObjetTarget != pObjetArene) {
					LOGGER.debug("found objet {} ",lObjetTarget);
					Position lPos2 = lObjetTarget.getPosition();
					if (compareProx(lPos1, lPos2, pRayon) && lObjetTarget.isVisible()) {
						LOGGER.debug("\tis in range");
						lRes.add(lObjetTarget);//add object detected
						
					}
				}
			}
		}
		
		return lRes;
		
	}
	
	
	boolean compareProx (Position p1, Position p2, int pRayon) {
		return Math.abs(p1.getX()-p2.getX()) <= pRayon &&
				       Math.abs(p1.getY()-p2.getY()) <= pRayon;
			
	}
	
	
	public ObjetArene getNearestObjet (ObjetArene pObjetArene) {
		double lMax = Double.MAX_VALUE;
		ObjetArene lRes = null;
		for (ModuleArena lModule : mArene.getLstCaseMain()) {
			if (lModule.isRobot()) {
				ObjetArene lRobot = lModule.getObjetArene();
				if (lRobot != pObjetArene && lRobot.isVisible()) {
					LOGGER.debug("For Robot {}",lRobot);
					double lDis = lRobot.getPosition().distance(pObjetArene.getPosition());
					LOGGER.debug("\t dis is {}", lDis);
					if (lDis < lMax) {
						lRes = lRobot;
						lMax = lDis;
					}
					
				}
			}
		}
		if (lRes != null) {
		   LOGGER.debug("Nearest Robot {}",lRes);
		}
		else {
			LOGGER.debug("Neared Robot not found");
		}
		
		return lRes;
		
	}
	
	
	
}
