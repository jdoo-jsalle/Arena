package com.js.dawa.prog.instruction;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.arene.ScanerObjet;
import com.js.dawa.model.position.Position;

public class DeplaOnOtherObject {
	
	ObjetArene mRobot;
	
	Arene mArene;
	
	ScanerObjet mScanner;
	
	ObjetArene mNearest;
	
	
	
	public DeplaOnOtherObject (ObjetArene pObjetArene, Arene pArene) {
		mRobot = pObjetArene;
		mArene = pArene;
		mScanner = new ScanerObjet();
		mScanner.init(pArene);
	}
	
	
	void follow () {
		vector (1);
	}
	
	void escape () {
		vector(-1);
	}
	
	
	void vector (int pDir) {
		ObjetArene lNearObjet = mScanner.getNearestObjet(mRobot);
		if (lNearObjet != null) {
			Position lRes = mRobot.getPosition().getVector(lNearObjet.getPosition());
			double lX = lRes.getX() * pDir; //inverse if necessary)
			double lY = lRes.getY() * pDir; //idem
			
			if (mArene != null && mArene.isNewPositionIsOk(mRobot, lX, lY)){
				mRobot.setInArena(true);
				mRobot.add(lX,lY);
			}
			else {
				mRobot.setInArena(false);
			}
		}
		
		mNearest = lNearObjet;
	}

	public ObjetArene getNearest () {
		return mNearest;
	}
	
}
