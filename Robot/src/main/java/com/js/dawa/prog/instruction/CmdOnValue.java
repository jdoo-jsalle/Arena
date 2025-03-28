package com.js.dawa.prog.instruction;

import com.js.dawa.robot.model.Robot;


public interface CmdOnValue {
	
	void init (Robot pRobot);
	
	String computeVal (String pVal);

}
