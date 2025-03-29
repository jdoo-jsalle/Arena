package com.js.dawa.prog.instruction;

import com.js.dawa.iu.arene.ObjetArene;

public class CmdOnValueVariable implements CmdOnValue{

	ObjetArene mRobot;
	
	@Override
	public void init(ObjetArene pRobot) {
		mRobot = pRobot;
		
	}

	@Override
	public String computeVal(String pVal) {
		//Data name is after fisrt car
		return mRobot.getDataBoard().getVariable(pVal.substring(1));
		
	}

}
