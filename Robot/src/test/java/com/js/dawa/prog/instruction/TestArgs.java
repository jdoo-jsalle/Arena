package com.js.dawa.prog.instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.js.dawa.model.robot.DataBoard;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.util.DawaException;

class TestArgs {
	
	private static final Logger LOGGER =  LogManager.getLogger( TestArgs.class );
	
	
	
	@Test
	void test_getArgs () {
		DataBoard lDataBoard = new DataBoard();
		lDataBoard.setVariable("depla", "10");
		Robot lRobot = new Robot();
		lRobot.setRobotData(lDataBoard);
		Args lArgs = new Args(lRobot);
		lArgs.addArguments("$depla");
		lArgs.addArguments("1");
		
		
		
		try {
			int lval = lArgs.getArgsInt(0);
			assertEquals(10,lval);
			
			lval = lArgs.getArgsInt(1);
			assertEquals(1,lval);
			
		} catch (DawaException e) {
			LOGGER.debug("error", e);
		}
	}

}
