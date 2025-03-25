package com.js.dawa;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.AreneProps;
import com.js.dawa.iu.arene.CaseArene;
import com.js.dawa.iu.arene.Robot;
import com.js.dawa.iu.arene.RobotsProps;
import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.iu.console.ConsoleGraphique;
import com.js.dawa.prog.instruction.Args;
import com.js.dawa.prog.instruction.Avancer;


public class Launcher {
	
	private static final Logger LOGGER =  LogManager.getLogger( Launcher.class );
	
	
	
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
		RobotsProps lProps = new RobotsProps();
		lProps.setName("R");
		lProps.setColor("red");
		lRobot.init(lProps);
		
		lArene.addObjetArene(20, 20, lRobot);//pos Robot
		
		lCaseArene = new CaseArene();
		lCaseArene.setCaseAreneRender(new CaseAreneRenderDefaut());
		lArene.addObjetArene(22, 20, lCaseArene);
		
		
		lConsole.setListCase(lArene.getLstCase());
		
		AreneProps lAreneProps = new AreneProps();
		lAreneProps.setSize(30);
		lAreneProps.setTitle("Arene");
		
		lArene.setAreneProps(lAreneProps);

		lConsole.init(lArene);
		
		boolean lEnd = false;
		
		Avancer lAvancer = new Avancer();
		Args lArgs = new Args();
		lArgs.addArguments("1");
		lArgs.addArguments("1");
		lAvancer.init(lArgs, lRobot, lArene);
		
		while (!lEnd) {
			
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
		
				LOGGER.error("error", e);
			}
			
			lConsole.update();
			//simule deplacement
			lAvancer.exec(null);
			
			//compute/eval lEnd
			
			
		}
		
		
		
		
	}

}
