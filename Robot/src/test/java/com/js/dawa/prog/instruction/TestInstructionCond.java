package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import com.js.dawa.model.robot.Robot;

public class TestInstructionCond {
	
	 @ParameterizedTest
     @CsvSource({
        "$i, 1, $i < 10, true",
        "$j, 1, $i < 10, false"//var $j is missing
     })
	void test_instruction_cond(String pVariable, String pValeur, String pCond, boolean pAttempt) {
		InstructionCond lInstructionCond = new InstructionCond();
		Robot lRobot = new Robot ();
		lRobot.getDataBoard().setVariable(pVariable, pValeur);
		
		assertEquals(pValeur, lRobot.getDataBoard().getVariable(pVariable));
		
		Args lArg = new Args(lRobot);
		lArg.addArguments(pCond);
		lInstructionCond.init(lArg, lRobot, null);
		assertEquals(pAttempt,lInstructionCond.execCondition());
		
		
	}

}
