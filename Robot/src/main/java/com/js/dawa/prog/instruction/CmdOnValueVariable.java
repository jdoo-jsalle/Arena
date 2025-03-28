package com.js.dawa.prog.instruction;

import com.js.dawa.robot.model.Robot;

public class CmdOnValueVariable implements CmdOnValue{

	Robot mRobot;
	
	@Override
	public void init(Robot pRobot) {
		mRobot = pRobot;
		
	}

	@Override
	public String computeVal(String pVal) {
		//Data name is after fisrt car
		return mRobot.getRobotData().getVariable(pVal.substring(1));
		
	}

}
