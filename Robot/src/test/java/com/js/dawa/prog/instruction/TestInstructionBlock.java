package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.robot.Position;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.model.robot.RobotsProps;
import com.js.dawa.prog.parse.ParseLigneCmd;
import com.js.dawa.util.DawaException;

class TestInstructionBlock {
	
	 private static final Logger LOGGER =  LoggerFactory.getLogger( TestInstructionBlock.class );
	
	@BeforeEach
	void init() {
		IdBlock.reinitIdBlock();
	}
	
	@Test
	void test_executeIntruction () {
		
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
			assertEquals("""
					Block[-1]:<args empty>
					Fake fake : [A]
					""", ldumpInit);
			
			
			String ldump = lParseLigneCmd.getMain().dump("");
			assertEquals("""
					Block[0]:<args empty>
					Fake fake : [1]
					Fake fake : [2]
					Fake fake : [3]
					""", ldump);
			
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
			assertEquals("""
					Block[0]:<args empty>
					Affect :  affect : [d, 1]
					if : [truc==0]
					Block[1]:<args empty>
					----Affect :  affect : [truc, 1]
					----Fake fake : [1]
					----Fake fake : [2]
					else
					Block[2]:<args empty>
					----Affect :  affect : [truc, 0]
					----Fake fake : [3]
					----Fake fake : [4]
					endif
					""", ldump);
			
			LOGGER.debug("------ step 1");
			InfoExecIns lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("affect", lInfo.getLastInfoExec().getInstruction().getArgs().getNameInstruction());
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			LOGGER.debug("------ step 2");
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("affect", lInfo.getLastInfoExec().getInstruction().getArgs().getNameInstruction());
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			LOGGER.debug("------ step 3");
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("fake", lInfo.getLastInfoExec().getInstruction().getArgs().getNameInstruction());
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Fake fake : [3]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			
			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("fake", lInfo.getLastInfoExec().getInstruction().getArgs().getNameInstruction());
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Fake fake : [3]:,if : [truc==0]:Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Fake fake : [3]:,Block[2]:<args empty>:Affect :  affect : [truc, 0]:,Fake fake : [3]:,Fake fake : [4]:",lInfo.toString() );
			assertTrue(lInfo.isOver());
			

			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("affect", lInfo.getLastInfoExec().getInstruction().getArgs().getNameInstruction());
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			

			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("affect", lInfo.getLastInfoExec().getInstruction().getArgs().getNameInstruction());
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			

			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("fake", lInfo.getLastInfoExec().getInstruction().getArgs().getNameInstruction());
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Fake fake : [1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			
			

			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("fake", lInfo.getLastInfoExec().getInstruction().getArgs().getNameInstruction());
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Fake fake : [1]:,if : [truc==0]:Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Fake fake : [1]:,Block[1]:<args empty>:Affect :  affect : [truc, 1]:,Fake fake : [1]:,Fake fake : [2]:",lInfo.toString() );
			assertTrue(lInfo.isOver());
			
			

			lInfo = lParseLigneCmd.getMain().execInstruction();
			assertEquals("affect", lInfo.getLastInfoExec().getInstruction().getArgs().getNameInstruction());
			assertEquals("Block[0]:<args empty>:Affect :  affect : [d, 1]:",lInfo.toString() );
			assertTrue(!lInfo.isOver());
			
			
		}
		catch (DawaException le) {
			LOGGER.debug("error", le);
		}
			
	}
	
	@Test
	void test_replaceInstructionCurrent_Block () {
		
		InstructionBlock lInstructionBlock = new InstructionBlock();
		InsFake lInsFake = new InsFake("A","1");
		lInstructionBlock.addInstruction(lInsFake);
		lInsFake = new InsFake("B","2");
		lInstructionBlock.addInstruction(lInsFake);
		lInsFake = new InsFake("C","3");
		lInstructionBlock.addInstruction(lInsFake);
		
		
		assertEquals("""
				Block[0]:<args empty>
				Fake null : [A, 1]
				Fake null : [B, 2]
				Fake null : [C, 3]
				""", lInstructionBlock.dump(""));
		
		lInstructionBlock.mStep = 1;
		
		lInsFake = new InsFake("D","4");
		
		lInstructionBlock.replaceInstructionCurrent(lInsFake);
		
		
		assertEquals("""
				Block[0]:<args empty>
				Fake null : [A, 1]
				Fake null : [D, 4]
				Fake null : [C, 3]
				""", lInstructionBlock.dump(""));
		
	
		//add subblock
		
		InstructionBlock lInstructionBlockSub = new InstructionBlock();
		lInsFake = new InsFake("AA","1");
		lInstructionBlockSub.addInstruction(lInsFake);
		lInsFake = new InsFake("BB","2");
		lInstructionBlockSub.addInstruction(lInsFake);
		lInsFake = new InsFake("CC","3");
		lInstructionBlockSub.addInstruction(lInsFake);
		lInstructionBlock.mStep = 3;//firt Block is onsub block
		lInstructionBlockSub.mStep = 1;
		lInstructionBlock.addInstruction(lInstructionBlockSub);
		
		
		assertEquals("""
				Block[0]:<args empty>
				Fake null : [A, 1]
				Fake null : [D, 4]
				Fake null : [C, 3]
				Block[1]:<args empty>
				Fake null : [AA, 1]
				Fake null : [BB, 2]
				Fake null : [CC, 3]
				
				""", lInstructionBlock.dump(""));
		
		
		lInsFake = new InsFake("FF","5");
		
		lInstructionBlock.replaceInstructionCurrent(lInsFake);
		
		assertEquals("""
				Block[0]:<args empty>
				Fake null : [A, 1]
				Fake null : [D, 4]
				Fake null : [C, 3]
				Block[1]:<args empty>
				Fake null : [AA, 1]
				Fake null : [FF, 5]
				Fake null : [CC, 3]
				
				""", lInstructionBlock.dump(""));
		
	}
	
	@Test
	void test_replaceInstructionCurrent_Cond () {
		InstructionCond lInstructionCond = new InstructionCond();
		
		InsFake lInsFake = new InsFake("A","1");
		lInstructionCond.addInstructionIf(lInsFake);
		lInsFake = new InsFake("B","2");
		lInstructionCond.addInstructionIf(lInsFake);
		lInsFake = new InsFake("C","3");
		
		lInstructionCond.addInstructionIf(lInsFake);
		
		lInsFake = new InsFake("AA","1");
		lInstructionCond.addInstructionElse(lInsFake);
		lInsFake = new InsFake("BB","2");
		lInstructionCond.addInstructionElse(lInsFake);
		lInsFake = new InsFake("CC","3");
		lInstructionCond.addInstructionElse(lInsFake);
			
		assertEquals("""
				if <args empty>
				Block[0]:<args empty>
				----Fake null : [A, 1]
				----Fake null : [B, 2]
				----Fake null : [C, 3]
				else
				Block[1]:<args empty>
				----Fake null : [AA, 1]
				----Fake null : [BB, 2]
				----Fake null : [CC, 3]
				endif""", lInstructionCond.dump(""));
		
		lInstructionCond.mLstElse.mStep=0;
		lInstructionCond.mLstIf.mStep=2;
		lInstructionCond.mForceIf = true;
		
		lInsFake = new InsFake("F","5");
		
		lInstructionCond.replaceInstructionCurrent(lInsFake);
		
		assertEquals("""
				if <args empty>
				Block[0]:<args empty>
				----Fake null : [A, 1]
				----Fake null : [B, 2]
				----Fake null : [F, 5]
				else
				Block[1]:<args empty>
				----Fake null : [AA, 1]
				----Fake null : [BB, 2]
				----Fake null : [CC, 3]
				endif""", lInstructionCond.dump(""));
		
		lInstructionCond.mForceIf = false;
		lInstructionCond.mForceElse = true;
		
		lInsFake = new InsFake("GG","6");
		
		lInstructionCond.replaceInstructionCurrent(lInsFake);
		
		assertEquals("""
				if <args empty>
				Block[0]:<args empty>
				----Fake null : [A, 1]
				----Fake null : [B, 2]
				----Fake null : [F, 5]
				else
				Block[1]:<args empty>
				----Fake null : [GG, 6]
				----Fake null : [BB, 2]
				----Fake null : [CC, 3]
				endif""", lInstructionCond.dump(""));
		
	}
	
	
}
