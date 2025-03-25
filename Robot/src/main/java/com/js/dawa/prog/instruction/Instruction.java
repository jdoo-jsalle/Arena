package com.js.dawa.prog.instruction;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.Robot;
import com.js.dawa.robot.model.Position;

public interface Instruction {
	
	void init(Args pArgsInstruction,Robot pRobot,Arene pArene);
	
	/**
	 * exec, give a new position
	 * @return
	 */
	Position exec (Args pArgs);

}
