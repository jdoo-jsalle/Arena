package com.js.dawa;


import java.text.DecimalFormat;

import com.js.dawa.model.arene.Energie;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.robot.Robot;

public class InfoEnd {
	
	
	int mTour;
	
	ModuleArena mModule;
	
	
	
	public String toString () {
		String lLEnergie ="";
		Energie lEnergie = mModule.getObjetArene().getEnergie();
		if (!lEnergie.isEmpty()) {
			lLEnergie = " (Energie Left : " + Integer.toString(lEnergie.getTot()) + ")";
			
		}
		double lAverageByTurn = lEnergie.averageSpendByTurn(mTour);
		double lAverageDamageByTurn = lEnergie.averageDamageByTurn(mTour);
		DecimalFormat decimalFormat = new DecimalFormat("0.0");
		return ((Robot)mModule.getObjetArene()).getRobotProps().getNom() + " : in " + Integer.toString(mTour) + " t." + lLEnergie + "( " + decimalFormat.format(lAverageByTurn) +  " e/t)" +  "( " + decimalFormat.format(lAverageDamageByTurn) +  " -d/t)" ;
	}
	
	

}
