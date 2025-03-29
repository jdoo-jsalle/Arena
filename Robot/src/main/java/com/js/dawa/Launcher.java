package com.js.dawa;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.AreneProps;
import com.js.dawa.iu.arene.CaseArene;
import com.js.dawa.iu.arene.FireBall;
import com.js.dawa.iu.arene.ModuleArena;
import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.iu.console.ConsoleGraphique;
import com.js.dawa.prog.instruction.Args;
import com.js.dawa.prog.instruction.InsAffect;
import com.js.dawa.prog.instruction.InsAvancer;
import com.js.dawa.prog.instruction.InstructionBlock;
import com.js.dawa.prog.instruction.InstructionCond;
import com.js.dawa.robot.model.Position;
import com.js.dawa.robot.model.Robot;
import com.js.dawa.robot.model.RobotsProps;
import com.js.dawa.util.DawaException;


public class Launcher {
	
	private static final Logger LOGGER =  LogManager.getLogger( Launcher.class );
	
	
	
	public static void main(String[] args) {
	
		Launcher lLauncher = new Launcher();
		lLauncher.view();
		

	}
	
	
	void view () {
		
		
		ConsoleGraphique lConsole = new ConsoleGraphique();
	    Arene lArene = new Arene(lConsole);
		

		
		AreneProps lAreneProps = new AreneProps();
		lAreneProps.setSize(30);
		lAreneProps.setTitle("Arene");
		
		lArene.setAreneProps(lAreneProps);

		lConsole.init(lArene);
		
		boolean lEnd = false;
		
		
		
	
		
		
		
		
		
		
		try {

			
		
			List<ModuleArena> lLstModule = new ArrayList<>();
			lLstModule.add(createDefaultCase(10, 10));
			lLstModule.add(createDefaultCase(20, 20));
			lLstModule.add(createFireVall(5, 5));
			lLstModule.add(createModuleRobotWithHisPrg(lArene));
			lArene.setLstCase(lLstModule);
			
			
			
			
		
			
			//loop
			while (!lEnd) {
				
				
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
			
					LOGGER.error("error", e);
				}
				
				for (ModuleArena lModuleArena : lLstModule) {
					lModuleArena.getInstruction().execInstruction();
					
				}
				
				
					
				//compute/eval lEnd
				
				lConsole.update();
				
				
			}
			
			
		} catch (DawaException e) {
			LOGGER.error("error interpretor", e);;
		}
		
		
		
		
		
		
	}
	
	ModuleArena createModuleRobotWithHisPrg (Arene pArene) throws DawaException {
		//robot
		Robot lRobot = new Robot();
		lRobot.setPosition(new Position(22, 22));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("R");
		lProps.setColor("red");
		lRobot.init(lProps);
		
		lRobot.getRobotData().setVariable("depla", "1");
		//his prg
		
		
		
		InstructionBlock lPrg = new InstructionBlock();
		InsAvancer lAvancer1 = new InsAvancer();
		Args lArgs = new Args(lRobot);
		lArgs.addArguments("$depla");
		lArgs.addArguments("0");
		lAvancer1.init(lArgs, lRobot, pArene);
		lPrg.addInstruction(lAvancer1);
		
		
		//cond
		InstructionCond lInstructionCond = new InstructionCond();
		
		lPrg.addInstruction(lInstructionCond);
		lArgs = new Args(lRobot);
		lArgs.addArguments("block == true");//block : generique name for robot block state
		lInstructionCond.init(lArgs, lRobot, pArene);
		
		InsAffect lInsAffect1 = new InsAffect();
		 lArgs = new Args(lRobot);
		 lArgs.addArguments("depla");
		 lArgs.addArguments("JS:depla * -1");
		 lInsAffect1.init(lArgs, lRobot, pArene);
		 lInstructionCond.addInstructionIf(lInsAffect1);
		
		 
		 ModuleArena lModuleRobot = new ModuleArena();
		 lModuleRobot.setObjetArene(lRobot);
		 lModuleRobot.setInstruction(lPrg);
		 return lModuleRobot;
		
	}
	
	ModuleArena createDefaultCase (int px, int py) {
		CaseArene lCaseArene = new CaseArene();//case defaut
		lCaseArene.setCaseAreneRender(new CaseAreneRenderDefaut());
		lCaseArene.setPosition(new Position(px, py));
		
		 ModuleArena lModuleRobot = new ModuleArena();
		 lModuleRobot.setObjetArene(lCaseArene);
		 
		 
		 return lModuleRobot;

	}
	
	ModuleArena createFireVall (int px, int py) {
		FireBall lFireBall = new FireBall();
		lFireBall.setPosition(new Position(5,5));
		
		ModuleArena lModuleArene = new ModuleArena();
		lModuleArene.setObjetArene(lFireBall);
		
		return lModuleArene;
	}

}
