package com.js.dawa.model.arene;

import java.util.List;
import java.util.Random;

import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.model.position.Position;

public class CreateDefaultCase {
	
	static Random mRandom = new Random();
	
	public void createDefaultCase (Arene pArene,List<ModuleArena> pLstModule,int pPourcent) {
		int lSize = pArene.getAreneProps().getSize();
		
		CaseAreneRenderDefaut lDefautRender = new CaseAreneRenderDefaut();
		lDefautRender.getInfoRender().setColor("grey");
		
		for (int li = 1; li < lSize; li++) {
			for (int lj = 1; lj < lSize; lj++) {
			
				if (mRandom.nextInt(100) < pPourcent) { //1 chance sur 5 to add an obstacle
					CaseArene lCaseArene = new CaseArene();//case defaut
					lCaseArene.addCaseAreneRender(lDefautRender);
					lCaseArene.setPosition(new Position(li, lj));
					
					ModuleArena lModuleRobot = new ModuleArena();
					lModuleRobot.setObjetArene(lCaseArene);
					pLstModule.add(lModuleRobot);
				}
			}
		}
	}

}
