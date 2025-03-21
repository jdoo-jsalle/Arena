package com.js.dawa;

import java.util.HashMap;
import java.util.Map;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.CaseArene;
import com.js.dawa.iu.arene.Robot;
import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.iu.console.ConsoleGraphique;
import com.js.dawa.robot.model.Position;


public class Launcher {
	
	
	
	public static void main(String[] args) {
	
		Launcher lLauncher = new Launcher();
		lLauncher.view();
		

	}
	
	
	void view () {
		
		
	ConsoleGraphique lConsole = new ConsoleGraphique();
		
		CaseArene lCaseArene = new CaseArene();
		lCaseArene.setCaseAreneRender(new CaseAreneRenderDefaut());
		
		Arene lArene = new Arene(lConsole);
		lArene.addObjetArene(10, 10, lCaseArene);
		
		Robot lRobot = new Robot();
		lRobot.setSymbol("R");
		
		lArene.addObjetArene(20, 20, lRobot);
		
		lCaseArene = new CaseArene();
		lCaseArene.setCaseAreneRender(new CaseAreneRenderDefaut());
		lArene.addObjetArene(22, 20, lCaseArene);
		
		
		lConsole.setListCase(lArene.getLstCase());
		
		Map<String, String> lParams = new HashMap<String, String>();
		lParams.put("titre", "Arene");
		lConsole.init(lParams);
		
		
		
		while (true) {
			
			
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
		
				e.printStackTrace();
			}
			
			lConsole.update();
			//simule deplacement
			Position lPosition = lRobot.getPosition();
			lPosition.addX(1);
			
			
		}
		
		
		
		
	}

}
