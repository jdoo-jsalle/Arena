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
import com.js.dawa.prog.instruction.InsTir;
import com.js.dawa.prog.instruction.Instruction;
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
		
		
		try {

			
		
			List<ModuleArena> lLstModule = new ArrayList<>();
			lLstModule.add(createDefaultCase(10, 10));
			lLstModule.add(createDefaultCase(20, 20));
			lLstModule.add(createFireBall(lArene));
			lLstModule.add(createModuleRobotWithHisPrg(lArene));
			lLstModule.add(createModuleRobot2WithAleaDepla(lArene));
			lArene.setLstCase(lLstModule);
			
			
			
			boolean lEnd = false;
		
			
			//loop
			while (!lEnd) {
				
			
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
			
					LOGGER.error("error", e);
				}
				
				//execute prg
				for (ModuleArena lModuleArena : lLstModule) {
					Instruction lInstruction = lModuleArena.getInstruction();
					if (lInstruction != null)
					    lInstruction.execInstruction();
					
				}
				
				lArene.rmDisposeObjet();
				
				
					
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
	
	ModuleArena createModuleRobot2WithAleaDepla (Arene pArene) throws DawaException {
		//robot
		Robot lRobot = new Robot();
		lRobot.setPosition(new Position(19, 19));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("G");
		lProps.setColor("green");
		lRobot.init(lProps);
		
		//his prg
		
		
		InstructionBlock lInstructionBlock = new InstructionBlock();
		
		InsAvancer lAvancer1 = new InsAvancer();
		Args lArgs = new Args(lRobot);
		lArgs.addArguments("Rand[2]");
		lArgs.addArguments("Rand[2]");
		lAvancer1.init(lArgs, lRobot, pArene);
		
		lInstructionBlock.addInstruction(lAvancer1);
		
		InsTir lInsTir = new InsTir();
		lArgs = new Args(lRobot);
		lArgs.addArguments("Rand[2]");
		lArgs.addArguments("Rand[2]");
		lInsTir.init(lArgs, lRobot, pArene);
		
		lInstructionBlock.addInstruction(lInsTir);
		
		 
		 ModuleArena lModuleRobot = new ModuleArena();
		 lModuleRobot.setObjetArene(lRobot);
		 lModuleRobot.setInstruction(lInstructionBlock);
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
	
	ModuleArena createFireBall (Arene pArene) throws DawaException {
		FireBall lFireBall = new FireBall();
		lFireBall.setPosition(new Position(5,5));
		
		
		InsAvancer lAvancer1 = new InsAvancer();
		Args lArgs = new Args(lFireBall);
		lArgs.addArguments("1");
		lArgs.addArguments("1");
		lAvancer1.init(lArgs, lFireBall, pArene);
	
		
		ModuleArena lModuleArene = new ModuleArena();
		lModuleArene.setObjetArene(lFireBall);
		lModuleArene.setInstruction(lAvancer1);
		
		
		return lModuleArene;
	}

}
