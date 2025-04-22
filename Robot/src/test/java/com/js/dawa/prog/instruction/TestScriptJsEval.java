package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.script.ScriptException;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.js.dawa.model.robot.DataBoard;
import com.js.dawa.model.robot.Robot;

class TestScriptJsEval {
	
	@Test
	void testGenerateScript_cond () {
		DataBoard lDataBoard = new DataBoard();
		lDataBoard.setVariable("$truc", "10");
		lDataBoard.setVariable("$machin", "10");
		
		ScriptJsEval lIfEval = new ScriptJsEval("$truc > 5 && $truc < 10 && $machin == 3");
		String lScript = lIfEval.generateScript(lDataBoard,ScriptJsEval.SCRIPT_JS_COND);
		
		assertEquals("function condition (){if (10 > 5 && 10 < 10 && 10 == 3) {   return true;} else {   return false;}}condition ();", lScript);
		
	}
	
	
	
	
	
	@Test
	 void testEval_true() {
		ScriptJsEval lIfEval = new ScriptJsEval("$truc > 5 && $truc < 10 && $machin == 3");

		
		
		Robot lRobot = new Robot();
		DataBoard lRobotData = new DataBoard();
		lRobotData.setVariable("$truc", "9");
		lRobotData.setVariable("$machin", "3");
		lRobot.setRobotData(lRobotData);
		assertTrue(lIfEval.eval(lRobot));
	
		
		
	}
	


    @ParameterizedTest
    @CsvSource({
       "$truc > 5 && $truc < 10 && $machin == 3, $truc, 11, $machin, 3",
       "$troc > 5 && $truc < 10 && $machin == 3, $truc, 11, $machin, 3",
       "$truc_missing > 5 && $truc < 10 && $machin == 3, $truc, 11, $machin, 3",
    })
	
	void testEval_false(String pCond,String pVar1, String pVal1, String pVar2, String pVal2) {
		ScriptJsEval lIfEval = new ScriptJsEval(pCond);

		
		
		Robot lRobot = new Robot();
		DataBoard lRobotData = new DataBoard();
		lRobotData.setVariable(pVar1, pVal1);
		lRobotData.setVariable(pVar2, pVal2);
		lRobot.setRobotData(lRobotData);
		 assertFalse(lIfEval.eval(lRobot));
		
		
		
		
	}
	
	@Test
	void testcompute() {
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
