package com.js.dawa.prog.instruction;

import com.js.dawa.robot.model.Position;

public interface Instruction {
	
	void init();
	
	/**
	 * exec, give a new position
	 * @return
	 */
	Position exec ();

}
