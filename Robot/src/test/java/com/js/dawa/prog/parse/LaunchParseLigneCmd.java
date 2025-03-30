package com.js.dawa.prog.parse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.robot.Position;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.model.robot.RobotsProps;
import com.js.dawa.util.DawaException;

public class LaunchParseLigneCmd {
	
	 private static final Logger LOGGER =  LogManager.getLogger( LaunchParseLigneCmd.class );
	
	public static void main(String[] args) {
		
		Robot lRobot = new Robot();

		lRobot.setPosition(new Position(22, 22));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("R");
		lProps.setColor("red");
		lRobot.init(lProps);
		
		Arene lArene = new Arene(null);
		
		ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,lArene);
		
		
		try {
			lParseLigneCmd.parse( "truc (0,1)");
			lParseLigneCmd.parse( "if (truc=0)");
			lParseLigneCmd.parse( "    machin (0,2)");
			lParseLigneCmd.parse( "    bidule (1,1)");
			lParseLigneCmd.parse( "    yup (0,1)");
			lParseLigneCmd.parse( "else");
			lParseLigneCmd.parse( "    machin_1 (0,2)");
			lParseLigneCmd.parse( "    if (truc=4)");
			lParseLigneCmd.parse("         io (1,1,1,x,1,)");
			lParseLigneCmd.parse("         iop (1,1,1,x,1,)");
			lParseLigneCmd.parse( "    endif");
			lParseLigneCmd.parse( "    bidule_1 (1,1)");
			lParseLigneCmd.parse( "    yup_1 (0,1)");
			lParseLigneCmd.parse( "endif");
			LOGGER.info("");
			lParseLigneCmd.mMainLstInstruction.dump("");
			
			
		} catch (DawaException e) {
			LOGGER.error("error", e);
		}
		
	}

}
