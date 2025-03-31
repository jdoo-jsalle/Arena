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
import com.js.dawa.prog.instruction.InsAffect;
import com.js.dawa.prog.instruction.InsAvancer;
import com.js.dawa.prog.instruction.InsInvisible;
import com.js.dawa.prog.instruction.InsTir;
import com.js.dawa.prog.instruction.Instruction;
import com.js.dawa.prog.instruction.InstructionBlock;
import com.js.dawa.prog.instruction.InstructionCond;
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
			lLstModule.add(createModuleRobot3Hide(lArene));
			lArene.setLstCase(lLstModule);
		
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
	
	String getRandClause (String pVal) {
		return "Rand [" + pVal + "]";
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
		lArgs.addArguments(getRandClause("2"));
		lArgs.addArguments(getRandClause("2"));
		lAvancer1.init(lArgs, lRobot, pArene);
		
		lInstructionBlock.addInstruction(lAvancer1);
		
		InsTir lInsTir = new InsTir();
		lArgs = new Args(lRobot);
		lArgs.addArguments(getRandClause("2"));
		lArgs.addArguments(getRandClause("2"));
		lInsTir.init(lArgs, lRobot, pArene);
		
		lInstructionBlock.addInstruction(lInsTir);
		
		 
		 ModuleArena lModuleRobot = new ModuleArena();
		 lModuleRobot.setObjetArene(lRobot);
		 lModuleRobot.setInstruction(lInstructionBlock);
		 return lModuleRobot;
		
	}
	
	
	ModuleArena createModuleRobot3Hide (Arene pArene) throws DawaException {
		 final String INDICATEUR ="indicateur";
		//robot
		Robot lRobot = new Robot();
		lRobot.setPosition(new Position(15, 15));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("B");
		lProps.setColor("blue");
		lRobot.init(lProps);
		
		lRobot.getRobotData().setVariable("wait", "0");
		lRobot.getRobotData().setVariable(INDICATEUR, "0");
		
		//his prg
		
		//cond
		InstructionCond lInstructionCond = new InstructionCond();
				
		
		Args lArgs = new Args(lRobot);
		lArgs.addArguments("wait == 5");//block : generique name for robot block state
		lInstructionCond.init(lArgs, lRobot, pArene);
		
		//if wait ==0
		
		InstructionBlock lInstructionBlock = new InstructionBlock();
		lInstructionCond.addInstructionIf(lInstructionBlock);
		
		
		InsInvisible lInsInvisible = new InsInvisible();
		lInsInvisible.init(lArgs, lRobot, pArene);
		
		lInstructionBlock.addInstruction(lInsInvisible);
		
		//wait = 0
		InsAffect lInsAffect0 = new InsAffect();
		lArgs = new Args(lRobot);
		lArgs.addArguments("wait");
		lArgs.addArguments("0");
		lInsAffect0.init(lArgs, lRobot, pArene);
		lInstructionBlock.addInstruction(lInsAffect0);
		
		
		//if indicateur ==0
			InstructionCond lCond2 = new InstructionCond();
			Args lArgs2 = new Args(lRobot);
			lArgs2.addArguments("indicateur == 0");
			lCond2.init(lArgs2, lRobot, pArene);
			//depla alea
			InsAvancer lAvancer1 = new InsAvancer();
			Args lArgs3 = new Args(lRobot);
			lArgs3.addArguments("Rand[2]");
			lArgs3.addArguments("Rand[2]");
			lAvancer1.init(lArgs3, lRobot, pArene);
			lCond2.addInstructionIf(lAvancer1);
			//indicateur =1
			InsAffect lInsAffect1 = new InsAffect();
			lArgs = new Args(lRobot);
			lArgs.addArguments(INDICATEUR);
			lArgs.addArguments("1");
			lInsAffect1.init(lArgs, lRobot, pArene);
			
			lCond2.addInstructionIf(lInsAffect1);
			
			//else
			// indicateur =0
			lInsAffect1 = new InsAffect();
			lArgs = new Args(lRobot);
			lArgs.addArguments(INDICATEUR);
			lArgs.addArguments("0");
			lInsAffect1.init(lArgs, lRobot, pArene);
			lCond2.addInstructionElse(lInsAffect1);
		
		lInstructionBlock.addInstruction(lCond2);
		
		//else
		lInsAffect1 = new InsAffect();
		lArgs = new Args(lRobot);
		lArgs.addArguments("wait");
		lArgs.addArguments("JS:wait + 1");
		lInsAffect1.init(lArgs, lRobot, pArene);
		
		lInstructionCond.addInstructionElse(lInsAffect1);
		
		
		
		
		
		
		
		//
		 
		 ModuleArena lModuleRobot = new ModuleArena();
		 lModuleRobot.setObjetArene(lRobot);
		 lModuleRobot.setInstruction(lInstructionCond);
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
