package com.js.dawa.iu.console;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.arene.ObjetArene;

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
				ObjetArene lRobot = lModuleArena.getObjetArene();
				InfoRender lInfoRender = lRobot.getRender().getInfoRender();
				lRes.append(lComa);
				lRes.append(lInfoRender.getString());
				lRes.append(" : ");
				
				lRes.append(StringUtils.leftPad(lRobot.getEnergie().toString(), 10, "0"));//format print energie
				lComa = "\t";
			}
		}
		
		
		return lRes.toString();
	}

}
