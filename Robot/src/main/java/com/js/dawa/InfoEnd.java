package com.js.dawa;


import java.text.DecimalFormat;

import com.js.dawa.iu.arene.render.ColorRender;
import com.js.dawa.model.arene.Energie;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.robot.Robot;

/**
 * Classe représentant les informations de fin de partie pour un robot donné.
 * Permet d'afficher un résumé des performances du robot (énergie, nombre de tours, dégâts, etc.).
 */
public class InfoEnd {
	
	/** Numéro du tour où le robot a été éliminé ou a gagné */
	int mTour;
	
	/** ModuleArena associé (robot et ses propriétés) */
	ModuleArena mModule;
	
	/**
	 * Retourne une chaîne HTML résumant les statistiques du robot à la fin de la partie.
	 * @return Statistiques formatées (nom, énergie, moyenne par tour, dégâts, etc.)
	 */
	public String toString () {
		String lLEnergie ="";
		Energie lEnergie = mModule.getObjetArene().getEnergie();
		if (!lEnergie.isEmpty()) {
			lLEnergie = " (Energie restante : " + Integer.toString(lEnergie.getTot()) + ")";
			
		}
		double lAverageSpendByTurn = lEnergie.averageSpendByTurn(mTour);
		double lAverageDamageByLoop = lEnergie.averageDamageByTurn(mModule.getTotLoop());
		double lAverageSpendByLoop = lEnergie.averageSpendByTurn(mModule.getTotLoop());
		DecimalFormat decimalFormat = new DecimalFormat("0.0");
		Robot lRobot = ((Robot)mModule.getObjetArene());
		String lColor = lRobot.getColor();
		ColorRender lcolorRender = new ColorRender();
		lcolorRender.setColor(lColor);
		return 
				"<span style=\"color:" + lcolorRender.convertToHex()  + ";\">" +
				
				lRobot.getRobotProps().getNom() + " : en " + 
				Integer.toString(mTour) + " t." +
				Integer.toString(mModule.getTotLoop()) + " b." +
				 lLEnergie + 
				"( " + decimalFormat.format(lAverageSpendByTurn) +  " e./t.)" + 
				"( " + decimalFormat.format(lAverageSpendByLoop) +  " e./b.)" + 
				"( " + decimalFormat.format(lAverageDamageByLoop) +  " -d./b.)" +
				"</span>\t"	;
		
	}
	
	

}
