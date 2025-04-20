package com.js.dawa.prog.instruction;

import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.model.arene.ObjetArene;

public class CmdOnValueJavaScript implements CmdOnValue {
	private static final Logger LOGGER =  LoggerFactory.getLogger( CmdOnValueJavaScript.class );

	ObjetArene mRobot;

	@Override
	public void init(ObjetArene pRobot) {
		mRobot = pRobot;

	}

	@Override
	public String computeVal(String pVal)  {
		String lRes="";
		String lClause = pVal.substring(ArgString.JS.length());
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
