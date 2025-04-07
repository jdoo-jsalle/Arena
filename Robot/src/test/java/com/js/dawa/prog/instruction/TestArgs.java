package com.js.dawa.prog.instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;

import com.js.dawa.model.robot.DataBoard;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.util.DawaException;

class TestArgs {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( TestArgs.class );
	
	
	
	@Test
	void test_getArgs_integer () {
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

	
	@Test
	void test_getArgs_integer_var_missing () {
		DataBoard lDataBoard = new DataBoard();
		Robot lRobot = new Robot();
		lRobot.setRobotData(lDataBoard);
		Args lArgs = new Args(lRobot);
		lArgs.addArguments("$depla");
		lArgs.addArguments("1");
		
		try {
			int lval = lArgs.getArgsInt(0);//missing depla
			assertEquals(0,lval);
			
			lval = lArgs.getArgsInt(1);
			assertEquals(1,lval);
			
		} catch (DawaException e) {
			LOGGER.debug("error", e);
		}
	}
}
