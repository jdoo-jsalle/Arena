package com.js.dawa;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.iu.console.ConsoleGraphique;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.AreneProps;
import com.js.dawa.model.arene.CaseArene;
import com.js.dawa.model.arene.FireBall;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.robot.Position;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.model.robot.RobotsProps;
import com.js.dawa.prog.instruction.Args;
import com.js.dawa.prog.instruction.InsAvancer;
import com.js.dawa.prog.instruction.Instruction;
import com.js.dawa.prog.parse.ParseLigneCmd;
import com.js.dawa.util.DawaException;
import com.js.dawa.util.DawaRunTimeException;


public class Launcher {
	
	private static final Logger LOGGER =  LogManager.getLogger( Launcher.class );
	
	static String SYNTAX_ERROR = "Syntax Error";
	
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
			lLstModule.add(createModuleRobotRedTransvers(lArene));
			lLstModule.add(createModuleRobotGreenAvanceAndShoot(lArene));
			lLstModule.add(createModuleRobotBlueHide(lArene));
			lArene.setLstCase(lLstModule);
		
			for (ModuleArena lModule : lLstModule) {
				lModule.init();
			}
			
			engineCompute(lConsole, lArene, lLstModule);
			
		} catch (DawaException e) {
			LOGGER.error("error interpretor", e);
		}
		
		
		
		
		
		
	}
	
	
	void engineCompute (ConsoleGraphique lConsole, Arene lArene, List<ModuleArena> lLstModule) throws DawaException {
		boolean lEnd = false;
		
		
		//loop
		//TODO : compute lEnd
		while (!lEnd) {
			
		
			
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				LOGGER.error("error", e);
				Thread.currentThread().interrupt();
				
			}
			
			//execute prg
			for (ModuleArena lModuleArena : lLstModule) {
				if (lModuleArena.isFonctionnel()) {
					Instruction lInstruction = lModuleArena.getInstructionLoop();
					if (lInstruction != null)
					    lInstruction.execInstruction();
				}
				
			}
			
			lArene.rmDisposeObjet();
			//compute/eval lEnd
			lConsole.update();
		}
		
	}
	
	
	
	ModuleArena createModuleRobotRedTransvers (Arene pArene) throws DawaException {
		//robot
		Robot lRobot = new Robot();
		lRobot.setPosition(new Position(22, 22));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("R");
		lProps.setColor("red");
		lRobot.init(lProps);
	    ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,pArene);
		
		try {
			lParseLigneCmd.parse("init");
			lParseLigneCmd.parse("affect (depla,1)");
			lParseLigneCmd.parse("endinit");
			lParseLigneCmd.parse("avancer ($depla,0)");
			lParseLigneCmd.parse("if (block==true)");
			lParseLigneCmd.parse("    affect (depla,JS:depla * -1)");
			lParseLigneCmd.parse("endif");
		}
		catch (DawaException le) {
			LOGGER.debug("error", le);
			throw new DawaRunTimeException(SYNTAX_ERROR);
		}
		 
		ModuleArena lModuleRobot = new ModuleArena();
		lModuleRobot.setObjetArene(lRobot);
		lModuleRobot.setInstructionLoop(lParseLigneCmd.getMain());
		lModuleRobot.setInstructionInit(lParseLigneCmd.getInit());
		return lModuleRobot;
		
	}
	

	ModuleArena createModuleRobotGreenAvanceAndShoot (Arene pArene) throws DawaException {
		//robot
		Robot lRobot = new Robot();
		lRobot.setPosition(new Position(19, 19));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("G");
		lProps.setColor("green");
		lRobot.init(lProps);
		
		//his prg
		ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,pArene);
		
		try {
		
			lParseLigneCmd.parse("avancer (Rand[2], Rand[2])");
			lParseLigneCmd.parse("tir (Rand[2], Rand[2])");
			
		}
		catch (DawaException le) {
			LOGGER.debug("error", le);
			throw new DawaRunTimeException(SYNTAX_ERROR);
		}
		 
		ModuleArena lModuleRobot = new ModuleArena();
		lModuleRobot.setObjetArene(lRobot);
		lModuleRobot.setInstructionLoop(lParseLigneCmd.getMain());
		lModuleRobot.setInstructionInit(lParseLigneCmd.getInit());
		
		
	
		 return lModuleRobot;
		
	}
	
	
	ModuleArena createModuleRobotBlueHide (Arene pArene) throws DawaException {
		//robot
		Robot lRobot = new Robot();
		lRobot.setPosition(new Position(15, 15));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("B");
		lProps.setColor("blue");
		lRobot.init(lProps);
		
		  ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,pArene);
		
		try {
			lParseLigneCmd.parse("init");
			lParseLigneCmd.parse("  affect (wait,0)");
			lParseLigneCmd.parse("  affect (indicateur,0)");
			lParseLigneCmd.parse("endinit");
			lParseLigneCmd.parse("if (wait == 5)");
			lParseLigneCmd.parse("  invisible ()");
			lParseLigneCmd.parse("  affect (wait,0)"); 
			lParseLigneCmd.parse("  if (indicateur == 0)");
			lParseLigneCmd.parse("    avancer (Rand[2], Rand[2])");
			lParseLigneCmd.parse("    affect (indicateur,1)");
			lParseLigneCmd.parse("  else ");
			lParseLigneCmd.parse("    affect (indicateur,0)");
			lParseLigneCmd.parse("  endif");
			lParseLigneCmd.parse("else ");
			lParseLigneCmd.parse("   affect (wait, JS:wait + 1)");
			lParseLigneCmd.parse("endif");

		}
		catch (DawaException le) {
			LOGGER.debug("error", le);
			throw new DawaRunTimeException(SYNTAX_ERROR);
		}
		 
		ModuleArena lModuleRobot = new ModuleArena();
		lModuleRobot.setObjetArene(lRobot);
		lModuleRobot.setInstructionLoop(lParseLigneCmd.getMain());
		lModuleRobot.setInstructionInit(lParseLigneCmd.getInit());
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
		lModuleArene.setInstructionLoop(lAvancer1);
		
		
		return lModuleArene;
	}

}
