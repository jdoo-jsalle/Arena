package com.js.dawa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.iu.console.AffichageInfoRobot;
import com.js.dawa.iu.console.Console;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.prog.instruction.Instruction;
import com.js.dawa.util.DawaException;

public class EngineViewer {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( EngineViewer.class );
	
	
	public void execEngineViewer (Arene pArene) throws DawaException {
		pArene.getConsole().init(pArene);
		
		
		
		for (ModuleArena lModule : pArene.getLstCaseMain()) {//init robot prg
			lModule.init();
		}
		
		engineCompute(pArene);
		
		
		
	}
	
	void engineCompute (Arene pArene) throws DawaException {
		boolean lEnd = false;
		
		Console lConsole = pArene.getConsole();
		
		
		AffichageInfoRobot lAffichageInfoRobot = new AffichageInfoRobot(pArene);
		//loop
		//TODO : compute lEnd
		int lTour = 1;
		while (!lEnd) {
			
		
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				LOGGER.error("error", e);
				Thread.currentThread().interrupt();
				
			}
			
			//execute prg
			List <ModuleArena> lLstModule = pArene.getLstCaseMain();
			for (ModuleArena lModuleArena : lLstModule) {
				if (lModuleArena.isFonctionnel()) {
					Instruction lInstruction = lModuleArena.getInstructionLoop();
					if (lInstruction != null)
					    lInstruction.execInstruction();
				}
				
			}
			
			pArene.updateListCase();
			
			pArene.rmDisposeObjet();
			//compute/eval lEnd
			StringBuilder lText = new StringBuilder();
			lText.append("<html>");
			lText.append("Step : " + Integer.toString(lTour));
			lText.append("<br>");
			
			lText.append(lAffichageInfoRobot.getAffichageInfoRobot());
			lText.append("<br>");
			lText.append("</html>");
			
			lConsole.setText(lText.toString());
			
			lTour++;
			lConsole.update();
		}
		
	}

}
