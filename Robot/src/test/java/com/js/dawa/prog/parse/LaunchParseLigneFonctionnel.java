package com.js.dawa.prog.parse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.robot.Position;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.model.robot.RobotsProps;

public class LaunchParseLigneFonctionnel {
	
	 private static final Logger LOGGER =  LogManager.getLogger( LaunchParseLigneFonctionnel.class );
	
	
	void parseProg () {
		Robot lRobot = new Robot();

		lRobot.setPosition(new Position(22, 22));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("R");
		lProps.setColor("red");
		lRobot.init(lProps);
		
		Arene lArene = new Arene(null);
		
		ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,lArene);
		
		try {
			lParseLigneCmd.parse( "affect (truc,1)");
			lParseLigneCmd.parse( "if (truc==0)");
			lParseLigneCmd.parse( "  affect (truc,1)");
			lParseLigneCmd.parse( "  fake (1)");
			lParseLigneCmd.parse( "  fake (2)");
			lParseLigneCmd.parse( "else ");
			lParseLigneCmd.parse( "  affect (truc,0)");
			lParseLigneCmd.parse( "  fake (3)");
			lParseLigneCmd.parse( "  fake (4)");
			lParseLigneCmd.parse(" endif");
			
			LOGGER.info("===> ",lParseLigneCmd.mMainLstInstruction.dump(""));
		
			for (int li = 0 ;li <30; li++)
			
			      lParseLigneCmd.mMainLstInstruction.execInstruction();
			
		}
		catch (Exception e) {
			LOGGER.debug("error", e);
		}
		
		
		
	}
	
	public static void main(String[] args) {
		LaunchParseLigneFonctionnel ll = new LaunchParseLigneFonctionnel();
		ll.parseProg();
	}

}
