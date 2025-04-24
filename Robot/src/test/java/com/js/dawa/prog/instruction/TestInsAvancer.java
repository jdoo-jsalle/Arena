package com.js.dawa.prog.instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.AreneProps;
import com.js.dawa.model.position.Position;
import com.js.dawa.model.robot.DataBoard;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.model.robot.RobotsProps;
import com.js.dawa.util.DawaException;

class TestInsAvancer {
	
	 private static final Logger LOGGER =  LogManager.getLogger( TestInsAvancer.class );
	
	
	@Test
	void testExecIntructionAvancer () {
		DataBoard lDataBoard = new DataBoard();
		lDataBoard.setVariable("depla", "1");
		Robot lRobot = new Robot();
		lRobot.setRobotData(lDataBoard);
		Args lArgs = new Args(lRobot);
		lArgs.addArguments("$depla");
		lArgs.addArguments("2");
		
		 lRobot = new Robot();
		lRobot.setRobotData(lDataBoard);
		lRobot.setPosition(new Position(0, 0));
		lRobot.init(new RobotsProps());
		
		
		InsAvancer lInsAvancer = new InsAvancer();
		Arene lArene = new Arene(null);
		AreneProps lAreneProp = new AreneProps();
		lAreneProp.setSize(100);
		lArene.setAreneProps(lAreneProp);
		
		try {
			
			
			assertEquals(0, lRobot.getPosition().getX());
			assertEquals(0, lRobot.getPosition().getY());
			
			lInsAvancer.init(lArgs, lRobot, lArene);
			lInsAvancer.execInstruction();
			
			assertEquals(1, lRobot.getPosition().getX());
			assertEquals(2, lRobot.getPosition().getY());
		
		} catch (DawaException e) {
			
			LOGGER.error("error",e);
		}
		
		
	}

}
