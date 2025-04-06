package com.js.dawa.iu.console;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.robot.Robot;

public class AffichageInfoRobot {
	
	Arene mArene;
	
	public AffichageInfoRobot (Arene pArene){
		mArene = pArene;
	}
	
	
	public String getAffichageInfoRobot() {
		StringBuilder lRes = new StringBuilder();
		List<ModuleArena> lLst = mArene.getLstCase();
		String lComa ="";
		for (ModuleArena lModuleArena : lLst) {
			if (lModuleArena.isRobot()) {
				Robot lRobot = (Robot)lModuleArena.getObjetArene();
				
				lRes.append(lComa);
				lRes.append(lRobot.getRobotProps().getNom());
				lRes.append(" : ");
				
				lRes.append(StringUtils.leftPad(lRobot.getEnergie().toString(), 10, "0"));//format print energie
				lComa = "\t";
			}
		}
		
		
		return lRes.toString();
	}

}
