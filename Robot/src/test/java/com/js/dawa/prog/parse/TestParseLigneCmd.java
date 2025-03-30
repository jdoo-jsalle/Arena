package com.js.dawa.prog.parse;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.js.dawa.model.robot.Robot;
import com.js.dawa.prog.instruction.Args;
import com.js.dawa.util.DawaException;

public class TestParseLigneCmd {
	
	 private static final Logger LOGGER =  LogManager.getLogger( TestParseLigneCmd.class );
	
	
	@Test
	public void testParse_erreur() {
		ParseLigneCmd lParseLigne = new ParseLigneCmd(null,null);
		try {
			lParseLigne.getArgs(0, "truc");
		} catch (DawaException e) {
			assertEquals("Ligne 0 error must have param beetween ()",e.getMessage());
					
		}
	}
	
	@Test
	public void testParse_normal_with_args() {
		Robot lRobot = new Robot();
		ParseLigneCmd lParseLigne = new ParseLigneCmd(lRobot,null);
	
		try {
			Args lArgs = lParseLigne.getArgs(0, "truc (40,bobo)");
			assertEquals("truc", lArgs.getNameInstruction());
			assertEquals(2, lArgs.sizeArgs());
			assertEquals("bobo",lArgs.getArgs(1) );
			assertEquals("40",lArgs.getArgs(0) );
			
		} catch (DawaException e) {
			
			assertFalse(e.getMessage(),true);
					
		}
	}
	
	@Test
	public void testParse_normal_without_args() {
		Robot lRobot = new Robot();
		ParseLigneCmd lParseLigne = new ParseLigneCmd(lRobot,null);
		try {
			Args lArgs = lParseLigne.getArgs(0, "truc ()");
			assertEquals(0, lArgs.sizeArgs());
		
			
		} catch (DawaException e) {
			
			assertFalse(e.getMessage(),true);
					
		}
	}
	
	

}
