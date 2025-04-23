package com.js.dawa;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.iu.console.AffichageInfoRobot;
import com.js.dawa.iu.console.ConsoleGraphique;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.AreneProps;
import com.js.dawa.model.arene.CreateDefaultCase;
import com.js.dawa.model.arene.Energie;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.robot.Position;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.model.robot.RobotsProps;
import com.js.dawa.prog.instruction.CostInstruction;
import com.js.dawa.prog.instruction.Instruction;
import com.js.dawa.prog.parse.ParseLigneCmd;
import com.js.dawa.util.DawaException;
import com.js.dawa.util.DawaRunTimeException;


public class LauncherDemo {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( LauncherDemo.class );
	
	static String SYNTAX_ERROR = "Syntax Error";
	
	int LIVE = 10000;
	
	CostInstruction mCostInstruction = new CostInstruction();
	

	
	static String IF ="If";
	static String ELSE ="else";
	static String END_IF ="endif";
	
	public static void main(String[] args) {
	
		LauncherDemo lLauncher = new LauncherDemo();
		lLauncher.view();


	}
	
	void initCostInstruction () {
		mCostInstruction.addCost("affect", 0);
		mCostInstruction.addCost("block", 0);
		mCostInstruction.addCost("if", 1);
		mCostInstruction.addCost("avancer", 4);
		mCostInstruction.addCost("fake", 0);
		mCostInstruction.addCost("tir", 3);
		mCostInstruction.addCost("invisible", 20);
		mCostInstruction.addCost("mine", 10);
		mCostInstruction.addCost("fuite", 4);
		mCostInstruction.addCost("poursuite", 4);
		
		
	}
	
	
	void view () {
		
		String lJavaVersion = System.getProperty("java.version");
		
		LOGGER.info("Begin Arena java version {}",lJavaVersion);
		LOGGER.debug("debug mode is active");
		ConsoleGraphique lConsole = new ConsoleGraphique();
	    Arene lArene = new Arene(lConsole);
	    initCostInstruction();
		

		
		AreneProps lAreneProps = new AreneProps();
		lAreneProps.setSize(30);
		lAreneProps.setTitle("Arene");
		
		lArene.setAreneProps(lAreneProps);

		lConsole.init(lArene);
		
		
		try {
			List<ModuleArena> lLstModule = new ArrayList<>();
			
			createDefaultCase(lArene, lLstModule);

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
	
	
	void engineCompute (ConsoleGraphique lConsole, Arene pArene, List<ModuleArena> lLstModule) throws DawaException {
		boolean lEnd = false;
		
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
			for (ModuleArena lModuleArena : lLstModule) {
				if (lModuleArena.isFonctionnel()) {
					Instruction lInstruction = lModuleArena.getInstructionLoop();
					if (lInstruction != null)
					    lInstruction.execInstruction();
				}
				
			}
			
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
		
		Energie lEnergie = new Energie ();
		lEnergie.setTot(LIVE);
		lRobot.setEnergie(lEnergie);
		
	    ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,pArene);
	    lParseLigneCmd.setCostInstruction(mCostInstruction);
		
		try {
			lParseLigneCmd.parse("init");
			lParseLigneCmd.parse("affect (depla,1)");
			lParseLigneCmd.parse("endinit");
			lParseLigneCmd.parse("avancer ($depla,0)");
			lParseLigneCmd.parse("if (block==true)");
			lParseLigneCmd.parse("    affect (depla,JS:depla * -1)");
			lParseLigneCmd.parse(END_IF);
			lParseLigneCmd.parse("scan(4)");
			lParseLigneCmd.parse("if (detected==true)");
			lParseLigneCmd.parse("  tir (Rand[2], Rand[2]) ");
			lParseLigneCmd.parse(ELSE);
			lParseLigneCmd.parse("  poursuite()");
			lParseLigneCmd.parse(END_IF);
			
		}
		catch (DawaException le) {
			LOGGER.debug(SYNTAX_ERROR, le);
			throw new DawaRunTimeException(SYNTAX_ERROR);
		}
		 
		ModuleArena lModuleRobot = new ModuleArena();
		lModuleRobot.setObjetArene(lRobot);
		lModuleRobot.setIsRobot();
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
		
		Energie lEnergie = new Energie ();
		lEnergie.setTot(LIVE);
		lRobot.setEnergie(lEnergie);
		
		//his prg
		ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,pArene);
		 lParseLigneCmd.setCostInstruction(mCostInstruction);
		
		try {
		
			lParseLigneCmd.parse("avancer (Rand[2], Rand[2])");
			lParseLigneCmd.parse("tir (Rand[2], Rand[2])");
			
		}
		catch (DawaException le) {
			LOGGER.debug(SYNTAX_ERROR, le);
			throw new DawaRunTimeException(SYNTAX_ERROR);
		}
		 
		ModuleArena lModuleRobot = new ModuleArena();
		lModuleRobot.setIsRobot();
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
		
		Energie lEnergie = new Energie ();
		lEnergie.setTot(LIVE);
		lRobot.setEnergie(lEnergie);
		
		ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,pArene);
		lParseLigneCmd.setCostInstruction(mCostInstruction);
		
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
			lParseLigneCmd.parse("    mine ()");
			lParseLigneCmd.parse("    affect (indicateur,1)");
			lParseLigneCmd.parse("  else ");
			lParseLigneCmd.parse("    affect (indicateur,0)");
			lParseLigneCmd.parse("  endif");
			lParseLigneCmd.parse(ELSE);
			lParseLigneCmd.parse("   affect (wait, JS:wait + 1)");
			lParseLigneCmd.parse(END_IF);

		}
		catch (DawaException le) {
			LOGGER.debug(SYNTAX_ERROR, le);
			throw new DawaRunTimeException(SYNTAX_ERROR);
		}
		 
		ModuleArena lModuleRobot = new ModuleArena();
		lModuleRobot.setObjetArene(lRobot);
		lModuleRobot.setIsRobot();
		lModuleRobot.setInstructionLoop(lParseLigneCmd.getMain());
		lModuleRobot.setInstructionInit(lParseLigneCmd.getInit());
		return lModuleRobot;
		 
		
		
	}
	
	void createDefaultCase (Arene pArene,List<ModuleArena> pLstModule) {
		
		CreateDefaultCase lCreate = new CreateDefaultCase();
		lCreate.createDefaultCase(pArene, pLstModule, 20);
	 

	}
	

}
