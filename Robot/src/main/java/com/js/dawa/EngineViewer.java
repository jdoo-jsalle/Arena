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
	
	ListInfoEnd mLstInfoEnd = new ListInfoEnd();
	
	Console mConsole;
	
	
	public void execEngineViewer (Arene pArene) throws DawaException {
		pArene.getConsole().init(pArene);
		
		
		
		for (ModuleArena lModule : pArene.getLstCaseMain()) {//init robot prg
			lModule.init();
		}
		
		engineCompute(pArene);
		
		
		
	}
	
	void engineCompute (Arene pArene) throws DawaException {
		boolean lEnd = false;
		
		mConsole = pArene.getConsole();
		
		
		AffichageInfoRobot lAffichageInfoRobot = new AffichageInfoRobot(pArene);
		//loop
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
			lEnd = !executePrg(lTour, lLstModule);
			
			pArene.updateListCase();//send object create in the tour to in main liste
			
			pArene.rmDisposeObjet();
			//compute/eval lEnd
			
			affichageCurrent(lTour, lAffichageInfoRobot, mConsole);
			lTour++;
			mConsole.update();
		}
		LOGGER.info("End");
		afficheEnd(mConsole);
		mConsole.update();//last console update
		
	}
	
	public void dispose() {
		if (mConsole != null) {
			mConsole.close();
		}
	}
	
	
	boolean  executePrg (int pTour, List <ModuleArena> lLstModule) throws DawaException {
		int ltotRun = 0;
		
		for (ModuleArena lModuleArena : lLstModule) {
			if (! lModuleArena.isOver()) {
				if (lModuleArena.isFonctionnel()) {
					if (lModuleArena.isRobot()) {
					    ltotRun ++;
					}
					Instruction lInstruction = lModuleArena.getInstructions();
					if (lInstruction != null)
					    lInstruction.execInstruction();
				}
				else {
					lModuleArena.setOver(true);
					mLstInfoEnd.addInfo(lModuleArena,pTour);
					
				}
			}
			
		}
		
		return ltotRun > 0;//last robot
	}
	
	
	void affichageCurrent (int pTour,AffichageInfoRobot pAffichageInfoRobot, Console pConsole ) {
		StringBuilder lText = new StringBuilder();
		lText.append("<html>");
		lText.append("Step : " + Integer.toString(pTour));
		lText.append("<br>");
		
		lText.append(pAffichageInfoRobot.getAffichageInfoRobot());
		lText.append("<br>");
		lText.append("</html>");
		
		pConsole.setText(lText.toString());
	}
	
	void afficheEnd ( Console pConsole ) {
		StringBuilder lText = new StringBuilder();
		lText.append("<html>");
		lText.append("Game is Over, ranking is :");
		lText.append("<br>");
		lText.append(mLstInfoEnd.toString());
		lText.append("<br>");
		lText.append("</html>");
		
		pConsole.setText(lText.toString());
		
	}

}
