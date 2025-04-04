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

class TestInstructionBlock {
	
	 private static final Logger LOGGER =  LogManager.getLogger( TestInstructionBlock.class );
	
	
	@Test
	void test_executeIntruction () {
		IdBlock.reinitIdBlock();
		Robot lRobot = new Robot();
		
		lRobot.setPosition(new Position(22, 22));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("R");
		lProps.setColor("red");
		lRobot.init(lProps);
		
		Arene lArene = new Arene(null);
		
		ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,lArene);
		
		
		try {
			lParseLigneCmd.parse("init");
			lParseLigneCmd.parse("  fake (A)");
			lParseLigneCmd.parse("endinit");
			lParseLigneCmd.parse( "fake (1)");
			lParseLigneCmd.parse( "fake (2)"); 
			lParseLigneCmd.parse( "fake (3)");
			
			String ldumpInit = lParseLigneCmd.getInit().dump("");
			assertEquals("Block[-1]:<args empty>\n"
					+ "Fake fake : [A]\n", ldumpInit);
			
			
			String ldump = lParseLigneCmd.getMain().dump("");
			assertEquals("Block[0]:<args empty>\n"
					+ "Fake fake : [1]\n"
					+ "Fake fake : [2]\n"
					+ "Fake fake : [3]\n", ldump);
			
			InfoExecIns lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Fake fake : [1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Fake fake : [1]:,Fake fake : [2]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Fake fake : [1]:,Fake fake : [2]:,Fake fake : [3]:",lInfo.toString() );
			assertTrue(lInfo.isOver());
			
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Fake fake : [1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			
			
		}
		catch (DawaException le) {
			LOGGER.debug("error", le);
		}
			
	}
	
	@Test
	void test_executeIntruction_with_cond () {
		IdBlock.reinitIdBlock();
		Robot lRobot = new Robot();

		lRobot.setPosition(new Position(22, 22));
		RobotsProps lProps = new RobotsProps();
		lProps.setName("R");
		lProps.setColor("red");
		lRobot.init(lProps);
		
		Arene lArene = new Arene(null);
		
		ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lRobot,lArene);
		
		
		try {
			lParseLigneCmd.parse( "affect (d,1)");
			lParseLigneCmd.parse( "if (truc==0)");
			lParseLigneCmd.parse( "  affect (truc,1)");
			lParseLigneCmd.parse( "  fake (1)");
			lParseLigneCmd.parse( "  fake (2)");
			lParseLigneCmd.parse( "else ");
			lParseLigneCmd.parse( "  affect (truc,0)");
			lParseLigneCmd.parse( "  fake (3)");
			lParseLigneCmd.parse( "  fake (4)");
			lParseLigneCmd.parse(" endif");
			
			String ldump = lParseLigneCmd.getMain().dump("");
			assertEquals("Block[0]:<args empty>\n"
					+ "Affect :  affect : [d, 1]\n"
					+ "if : [truc==0]\n"
					+ "Block[1]:<args empty>\n"
					+ "----Affect :  affect : [truc, 1]\n"
					+ "----Fake fake : [1]\n"
					+ "----Fake fake : [2]\n"
					+ "else\n"
					+ "Block[2]:<args empty>\n"
					+ "----Affect :  affect : [truc, 0]\n"
					+ "----Fake fake : [3]\n"
					+ "----Fake fake : [4]\n"
					+ "endif\n", ldump);
			
			LOGGER.debug("------ step 1");
			InfoExecIns lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			LOGGER.debug("------ step 2");
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			LOGGER.debug("------ step 3");
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Fake fake : [3]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Fake fake : [3]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Fake fake : [3]:,Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Fake fake : [3]:,Fake fake : [4]:",lInfo.toString() );
			assertTrue(lInfo.isOver());
			

			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			

			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			

			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Fake fake : [1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			
			

			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Fake fake : [1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Fake fake : [1]:,Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Fake fake : [1]:,Fake fake : [2]:",lInfo.toString() );
			assertTrue(lInfo.isOver());
			
			

			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			
			
		}
		catch (DawaException le) {
			LOGGER.debug("error", le);
		}
			
	}

}
