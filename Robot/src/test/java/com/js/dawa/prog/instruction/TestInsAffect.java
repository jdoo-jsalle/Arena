package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;

import com.js.dawa.model.robot.Robot;
import com.js.dawa.util.DawaException;

class TestInsAffect {
	
	 private static final Logger LOGGER =  LoggerFactory.getLogger( TestInsAffect.class );
	
	@Test
	void testExecInstruction_for_insAffect () {
		InsAffect lInsAffect = new InsAffect();
		
		
		
		try {
			
			Robot lRobot = new Robot();
			
			Args lArgs = new Args(lRobot);
			lArgs.addArguments("depla");
			lArgs.addArguments("10");
			
			assertNull( lRobot.getRobotData().getVariable("depla"));
			
			lInsAffect.init(lArgs, lRobot, null);
			lInsAffect.execInstruction();
			
			assertEquals("10", lRobot.getRobotData().getVariable("depla"));
			
			
			
			
		} catch (DawaException e) {
			
			LOGGER.error("error",e);
		}
		
		
	}

}
