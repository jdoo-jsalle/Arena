package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.robot.Position;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.model.robot.RobotsProps;
import com.js.dawa.prog.parse.ParseLigneCmd;
import com.js.dawa.util.DawaException;

public class TestInstructionBlock {
	
	 private static final Logger LOGGER =  LogManager.getLogger( TestInstructionBlock.class );
	
	
	@Test
	public void test_executeIntrusction () {
		Robot lRobot = new Robot();

		lRobot.setPosition(new Position(22, 22));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("R");
		lProps.setColor("red");
		lRobot.init(lProps);
		
		Arene lArene = new Arene(null);
		
		ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,lArene);
		
		
		try {
			lParseLigneCmd.parse( "fake (1)");
			lParseLigneCmd.parse( "fake (2)");
			lParseLigneCmd.parse( "fake (3)");
			
			String ldump = lParseLigneCmd.getMain().dump("");
			assertEquals("<args empty>\n"
					+ "Fake fake : [1]\n"
					+ "Fake fake : [2]\n"
					+ "Fake fake : [3]\n", ldump);
			
			InfoExecIns lInfo = lParseLigneCmd.getMain().execInstruction();
			assertTrue(!lInfo.isOver());
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertTrue(!lInfo.isOver());
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertTrue(lInfo.isOver());
			
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertTrue(!lInfo.isOver());
			
			
		}
		catch (DawaException le) {
			LOGGER.debug("error", le);
		}
			
	}

}
