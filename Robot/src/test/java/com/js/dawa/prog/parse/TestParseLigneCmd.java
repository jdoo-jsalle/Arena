package com.js.dawa.prog.parse;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;

import com.js.dawa.model.robot.Robot;
import com.js.dawa.prog.instruction.Args;
import com.js.dawa.util.DawaException;

class TestParseLigneCmd {
	
	 private static final Logger LOGGER =  LoggerFactory.getLogger( TestParseLigneCmd.class );
	
	
	@Test
	void testParse_erreur() {
		ParseLigneCmd lParseLigne = new ParseLigneCmd(null,null);
		try {
			lParseLigne.getArgs(0, "truc");
		} catch (DawaException e) {
			LOGGER.debug("Error",e);
			assertEquals("Ligne #0 \"truc\" : error must have param beetween ()",e.getMessage());
					
		}
	}
	
	@Test
	void testParse_normal_with_args() {
		Robot lRobot = new Robot();
		ParseLigneCmd lParseLigne = new ParseLigneCmd(lRobot,null);
	
		try {
			Args lArgs = lParseLigne.getArgs(0, "truc (40,bobo)");
			assertEquals("truc", lArgs.getNameInstruction());
			assertEquals(2, lArgs.sizeArgs());
			assertEquals("bobo",lArgs.getArgs(1) );
			assertEquals("40",lArgs.getArgs(0) );
			
		} catch (DawaException e) {
			LOGGER.debug("error",  e);
			assertFalse(e.getMessage(),true);
					
		}
	}
	
	@Test
	void testParse_normal_without_args() {
		Robot lRobot = new Robot();
		ParseLigneCmd lParseLigne = new ParseLigneCmd(lRobot,null);
		try {
			Args lArgs = lParseLigne.getArgs(0, "truc ()");
			assertEquals(0, lArgs.sizeArgs());
		
			
		} catch (DawaException e) {
			LOGGER.debug("error",e);
			assertFalse(e.getMessage(),true);
					
		}
	}
	
	

}
