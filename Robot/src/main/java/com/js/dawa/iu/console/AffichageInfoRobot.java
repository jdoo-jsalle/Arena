package com.js.dawa.iu.console;

import org.apache.commons.lang3.StringUtils;

import com.js.dawa.iu.arene.render.ColorRender;
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
		
		String lComa ="";
		for (ModuleArena lModuleArena : mArene.getLstCaseMain()) {
			if (lModuleArena.isRobot()) {
				Robot lRobot = (Robot)lModuleArena.getObjetArene();
				String lColor = lRobot.getColor();
				ColorRender lcolorRender = new ColorRender();
				lcolorRender.setColor(lColor); 
				lRes.append(lComa);
				lRes.append("<span style=\"color:" + lcolorRender.convertToHex() + ";\">");
				lRes.append(lRobot.getRobotProps().getNom());
				lRes.append(" : ");
				
				lRes.append(StringUtils.leftPad(lRobot.getEnergie().toString(), 10, "0"));//format print energie
				lRes.append("</span>");
				lComa = "\t";
			}
		}
		
		
		return lRes.toString();
	}

}
