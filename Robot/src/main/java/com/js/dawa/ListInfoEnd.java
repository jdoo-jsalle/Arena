package com.js.dawa;

import java.util.ArrayList;
import java.util.List;

import com.js.dawa.model.arene.ModuleArena;

public class ListInfoEnd {
	
	List<InfoEnd> mLstEnd = new ArrayList<>();
	
	
	
	void addInfo (ModuleArena pModuleArena, int pTour) {
		if (pModuleArena.isRobot()) {
			InfoEnd lInfoEnd = new InfoEnd();
			lInfoEnd.mModule = pModuleArena;
			lInfoEnd.mTour = pTour;
			mLstEnd.add(lInfoEnd);
		}
		
	}
	
	public String getWinner () {
		return "";
	}
	
	
	public String toString () {
		return mLstEnd.toString();
	}

}
