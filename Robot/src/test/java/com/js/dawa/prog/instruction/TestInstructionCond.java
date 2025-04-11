package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.js.dawa.model.robot.Robot;

public class TestInstructionCond {
	
	@Test
	void test_instruction_cond() {
		InstructionCond lInstructionCond = new InstructionCond();
		Robot lRobot = new Robot ();
		lRobot.getDataBoard().setVariable("$i", "1");
		
		assertEquals("1", lRobot.getDataBoard().getVariable("$i"));
		
		Args lArg = new Args(lRobot);
		lArg.addArguments("$i < 10");
		lInstructionCond.init(lArg, lRobot, null);
		assertTrue(lInstructionCond.execCondition());
		
		
	}

}
