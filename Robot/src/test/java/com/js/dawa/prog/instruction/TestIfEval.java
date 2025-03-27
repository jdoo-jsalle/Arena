package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.script.ScriptException;

import org.junit.Test;

import com.js.dawa.robot.model.Robot;
import com.js.dawa.robot.model.DataBoard;

public class TestIfEval {
	
	@Test
	public void testGenerateScript () {
		DataBoard lDataBoard = new DataBoard();
		lDataBoard.setVariable("$truc", "10");
		lDataBoard.setVariable("$machin", "10");
		
		IfEval lIfEval = new IfEval("$truc > 5 && $truc < 10 && $machin == 3");
		String lScript = lIfEval.generateScript(lDataBoard);
		
		assertEquals("function condition (){if (10 > 5 && 10 < 10 && 10 == 3) {   return true;} else {   return false;}}condition ();", lScript);
		
	}


	@Test
	public void testEval_true() {
		IfEval lIfEval = new IfEval("$truc > 5 && $truc < 10 && $machin == 3");

		
		
		Robot lRobot = new Robot();
		DataBoard lRobotData = new DataBoard();
		lRobotData.setVariable("$truc", "9");
		lRobotData.setVariable("$machin", "3");
		lRobot.setRobotData(lRobotData);
		try {
			assertTrue(lIfEval.eval(lRobot));
		}
		catch (ScriptException e) {
			assertTrue (e.getMessage(),false);
		}
		
		
	}
	
	@Test
	public void testEval_false() {
		IfEval lIfEval = new IfEval("$truc > 5 && $truc < 10 && $machin == 3");

		
		
		Robot lRobot = new Robot();
		DataBoard lRobotData = new DataBoard();
		lRobotData.setVariable("$truc", "11");
		lRobotData.setVariable("$machin", "3");
		lRobot.setRobotData(lRobotData);
		try {
			assertFalse(lIfEval.eval(lRobot));
		}
		catch (ScriptException e) {
			assertTrue (e.getMessage(),false);
		}
		
		
	}
}
