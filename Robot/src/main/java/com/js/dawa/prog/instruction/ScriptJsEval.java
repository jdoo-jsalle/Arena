package com.js.dawa.prog.instruction;

import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.robot.DataBoard;

public class ScriptJsEval {
	
	 private static final Logger LOGGER =  LogManager.getLogger( ScriptJsEval.class );
	 
	 static ScriptEngineManager factory = new ScriptEngineManager();
     static ScriptEngine engine = factory.getEngineByName("graal.js");
	 
	 static String SCRIPT_JS_COND ="function condition (){"
				+ "if (#CLAUSE#) {"
				+ "   return true;"
				+ "}"
				+ " else {"
				+ "   return false;"
				+ "}"
				+ "}"
				+ "condition ();";
	 
	 static String SCRIPT_JS_COMPUTE ="function compute (){"
				+ "    return #CLAUSE#;" 
				+ "}"
				+ "compute();";
	
	 /**
	  * Condition
	  *   $truc == 1 || $machin < 12 (java script syntaxe
	  * Compute 
	  *    12 * $machin * 13 + 1
	  */
	String mClause;
	
	public ScriptJsEval (String pClause) {
		mClause = pClause;
		
	}
	
	
	public String generateScript (DataBoard lDataBoard,String pScript) {
		
		String lCond = mClause;
		Map<String, String> lLstVars = lDataBoard.getLstVar();
		for (Entry<String, String> le : lLstVars.entrySet()) {
			String lVar = le.getKey();
			String lValue = le.getValue();
			lCond = lCond.replace(lVar, lValue);
			
		}

		return pScript.replace("#CLAUSE#", lCond);
	}
	
	
	public boolean eval (ObjetArene pRobot) throws  ScriptException {
		String lClause = generateScript(pRobot.getDataBoard(),SCRIPT_JS_COND);
		LOGGER.debug("Clause cond is {}", lClause);
		return ((Boolean)engine.eval(lClause)).booleanValue();
	}
	
	
	public String compute(ObjetArene pRobot) throws  ScriptException {
		String lClause = generateScript(pRobot.getDataBoard(),SCRIPT_JS_COMPUTE);
		LOGGER.debug("Clause cond is {}", lClause);
		return (engine.eval(lClause).toString());
		
		
	}
	


}
