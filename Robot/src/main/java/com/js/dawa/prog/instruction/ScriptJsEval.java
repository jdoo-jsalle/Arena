package com.js.dawa.prog.instruction;

import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.robot.DataBoard;

public class ScriptJsEval {
	
	 private static final Logger LOGGER =  LoggerFactory.getLogger( ScriptJsEval.class );
	 
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
			LOGGER.debug("found key {} with value {}",lVar,lValue);
			lCond = lCond.replace(lVar, lValue);
			
		}

		return pScript.replace("#CLAUSE#", lCond);
	}
	
	
	public boolean eval (ObjetArene pRobot) throws  ScriptException  {
		
		
	    boolean lRes = false;
	    String lClause ="";
	    try {
			lClause = generateScript(pRobot.getDataBoard(),SCRIPT_JS_COND);
			LOGGER.debug("Clause cond is {}", lClause);
			lRes = ((Boolean)engine.eval(lClause)).booleanValue();
	    }
	    catch (ScriptException le) {
	    	LOGGER.info("Script is : {}",lClause);
	    	LOGGER.error("error script " ,le);
	    	
	    }

		
		return lRes;
	}
	
	String getObjetAreneString (ObjetArene pObjet) {
		return pObjet != null ? pObjet.toString() : "is null";
	}
	
	
	public String compute(ObjetArene pRobot) throws  ScriptException {
		String lRes= "";
		try {
			String lClause = generateScript(pRobot.getDataBoard(),SCRIPT_JS_COMPUTE);
			LOGGER.debug("Clause cond is {}", lClause);
			lRes = (engine.eval(lClause).toString());
		 }
	    catch (ScriptException le) {
	    	LOGGER.error("error script",le);
	    	
	    }
		
		return lRes;
				
		
		
	}
	


}
