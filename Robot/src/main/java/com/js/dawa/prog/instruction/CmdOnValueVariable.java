package com.js.dawa.prog.instruction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.model.arene.ObjetArene;

public class CmdOnValueVariable implements CmdOnValue{
	
	private static final Logger LOGGER =  LogManager.getLogger( CmdOnValueVariable.class );

	ObjetArene mRobot;
	
	@Override
	public void init(ObjetArene pRobot) {
		mRobot = pRobot;
		
	}

	@Override
	public String computeVal(String pVal) {
		//Data name is after fisrt car
		String lVariableName = pVal.substring(1);
		String lRes = mRobot.getDataBoard().getVariable(lVariableName);
		LOGGER.debug("seek variable {} is {}",lVariableName,lRes);
		return lRes;
		
	}

}
