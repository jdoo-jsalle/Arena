package com.js.dawa.prog.instruction;

import javax.script.ScriptException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.robot.model.Robot;


public class CmdOnValueJavaScript implements CmdOnValue {
	private static final Logger LOGGER =  LogManager.getLogger( CmdOnValueJavaScript.class );

	Robot mRobot;

	@Override
	public void init(Robot pRobot) {
		mRobot = pRobot;

	}

	@Override
	public String computeVal(String pVal)  {
		String lRes="";
		String lClause = pVal.substring(Args.JS.length());
		LOGGER.debug("Clause {}", lClause);
		ScriptJsEval lScriptEval = new ScriptJsEval(lClause);
	
		
		try {
			lRes = lScriptEval.compute(mRobot);
		} catch (ScriptException e) {
			LOGGER.debug("error",  e);
			
		}
		return lRes;
	}

}
