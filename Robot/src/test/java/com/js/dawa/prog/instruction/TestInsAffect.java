package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.js.dawa.robot.model.Robot;
import com.js.dawa.util.DawaException;

public class TestInsAffect {
	
	 private static final Logger LOGGER =  LogManager.getLogger( TestInsAffect.class );
	
	@Test
	public void testExecInstruction_for_insAffect () {
		InsAffect lInsAffect = new InsAffect();
		
		
		
		try {
			
			Robot lRobot = new Robot();
			
			Args lArgs = new Args(lRobot.getRobotData());
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
