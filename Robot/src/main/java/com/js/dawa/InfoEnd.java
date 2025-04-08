package com.js.dawa;

import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.robot.Robot;

public class InfoEnd {
	
	
	int mTour;
	
	ModuleArena mModule;
	
	public String toString () {
		return ((Robot)mModule.getObjetArene()).getRobotProps().getNom() + " : in " + Integer.toString(mTour) + " t.";
	}
	
	

}
