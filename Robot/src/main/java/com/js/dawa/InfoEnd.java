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
		double lAverageSpendByTurn = lEnergie.averageSpendByTurn(mTour);
		double lAverageDamageByLoop = lEnergie.averageDamageByTurn(mModule.getTotLoop());
		double lAverageSpendByLoop = lEnergie.averageSpendByTurn(mModule.getTotLoop());
		DecimalFormat decimalFormat = new DecimalFormat("0.0");
		Robot lRobot = ((Robot)mModule.getObjetArene());
		return 
				"<span style=\"color:" + lRobot.getColor() + ";\">" +
				
				lRobot.getRobotProps().getNom() + " : in " + 
				Integer.toString(mTour) + " t." +
				Integer.toString(mModule.getTotLoop()) + " b." +
				 lLEnergie + 
				"( " + decimalFormat.format(lAverageSpendByTurn) +  " e./t.)" + 
				"( " + decimalFormat.format(lAverageSpendByLoop) +  " e./b.)" + 
				"( " + decimalFormat.format(lAverageDamageByLoop) +  " -d./b.)" +
				"</span>\t"	;
		
	}
	
	

}
