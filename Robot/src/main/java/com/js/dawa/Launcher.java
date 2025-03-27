package com.js.dawa;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.AreneProps;
import com.js.dawa.iu.arene.CaseArene;
import com.js.dawa.iu.arene.FireBall;
import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.iu.console.ConsoleGraphique;
import com.js.dawa.prog.instruction.Args;
import com.js.dawa.prog.instruction.InsAffect;
import com.js.dawa.prog.instruction.InsAvancer;
import com.js.dawa.prog.instruction.InstructionBlock;
import com.js.dawa.prog.instruction.InstructionCond;
import com.js.dawa.prog.objet.ComputePosition;
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
		
		//Objet Ephemere
		ComputePosition lComputePosition = new ComputePosition(lArene);
		FireBall lFireBall = new FireBall();
		lFireBall.setPosition(new Position(5,5));
		lComputePosition.addObjet(lFireBall);
		lArene.setLstCaseEphemere(lComputePosition.getLstObjet());;
	
		
		AreneProps lAreneProps = new AreneProps();
		lAreneProps.setSize(30);
		lAreneProps.setTitle("Arene");
		
		lArene.setAreneProps(lAreneProps);

		lConsole.init(lArene);
		
		boolean lEnd = false;
		
		
		
		
		
		InstructionCond lInstructionCond = new InstructionCond();
		
		
		try {

			lRobot.getRobotData().setVariable("depla", "1");
			
			InstructionBlock lPrg = new InstructionBlock();
			InsAvancer lAvancer1 = new InsAvancer();
			Args lArgs = new Args(lRobot.getRobotData());
			lArgs.addArguments("$depla");
			lArgs.addArguments("0");
			lAvancer1.init(lArgs, lRobot, lArene);
			lPrg.addInstruction(lAvancer1);
			
			
			//cond
			
			
			lArgs = new Args(lRobot.getRobotData());
			lArgs.addArguments("block == true");
			lInstructionCond.init(lArgs, lRobot, lArene);
			
			InsAffect lInsAffect1 = new InsAffect();
			 lArgs = new Args(lRobot.getRobotData());
			 lArgs.addArguments("depla");
			 lArgs.addArguments("JS:depla * -1");
			 lInsAffect1.init(lArgs, lRobot, lArene);
			 lInstructionCond.addInstructionIf(lInsAffect1);
		
			
			
			
			
			
			lPrg.addInstruction(lInstructionCond);
			
			//loop
			while (!lEnd) {
				
				
				
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
			
					LOGGER.error("error", e);
				}
				
				lConsole.update();
				//simule deplacement
				lPrg.execInstruction();
				
				lComputePosition.computeNewPosition();
				
				//compute/eval lEnd
				
				
			}
			
			
		} catch (DawaException e) {
			LOGGER.error("error interpretor", e);;
		}
		
		
		
		
		
		
	}

}
