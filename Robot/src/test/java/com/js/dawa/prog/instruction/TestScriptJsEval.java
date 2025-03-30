package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.script.ScriptException;

import org.junit.Test;

import com.js.dawa.model.robot.DataBoard;
import com.js.dawa.model.robot.Robot;

public class TestScriptJsEval {
	
	@Test
	public void testGenerateScript_cond () {
		DataBoard lDataBoard = new DataBoard();
		lDataBoard.setVariable("$truc", "10");
		lDataBoard.setVariable("$machin", "10");
		
		ScriptJsEval lIfEval = new ScriptJsEval("$truc > 5 && $truc < 10 && $machin == 3");
		String lScript = lIfEval.generateScript(lDataBoard,ScriptJsEval.SCRIPT_JS_COND);
		
		assertEquals("function condition (){if (10 > 5 && 10 < 10 && 10 == 3) {   return true;} else {   return false;}}condition ();", lScript);
		
	}


	@Test
	public void testEval_true() {
		ScriptJsEval lIfEval = new ScriptJsEval("$truc > 5 && $truc < 10 && $machin == 3");

		
		
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
		ScriptJsEval lIfEval = new ScriptJsEval("$truc > 5 && $truc < 10 && $machin == 3");

		
		
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
	
	@Test
	public void testcompute() {
		ScriptJsEval lIfEval = new ScriptJsEval("$truc * 10 + $machin");

		
		
		Robot lRobot = new Robot();
		DataBoard lRobotData = new DataBoard();
		lRobotData.setVariable("$truc", "11");
		lRobotData.setVariable("$machin", "3");
		lRobot.setRobotData(lRobotData);
		try {
			assertEquals("113",lIfEval.compute(lRobot));
		}
		catch (ScriptException e) {
			assertTrue (e.getMessage(),false);
		}
		
		
	}
}
