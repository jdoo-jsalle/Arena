package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.robot.model.DataBoard;
import com.js.dawa.robot.model.Robot;

public class IfEval {
	
	 private static final Logger LOGGER =  LogManager.getLogger( IfEval.class );
	 
	 static ScriptEngineManager factory = new ScriptEngineManager();
     static ScriptEngine engine = factory.getEngineByName("graal.js");
	 
	 static String SCRIPT_JS ="function condition (){"
				+ "if (COND) {"
				+ "   return true;"
				+ "}"
				+ " else {"
				+ "   return false;"
				+ "}"
				+ "}"
				+ "condition ();";
	
	 /**
	  * $truc == 1 || $machin < 12 (java script syntaxe
	  */
	String mClauseCondition;
	
	public IfEval (String pClauseCondition) {
		mClauseCondition = pClauseCondition;
		
	}
	
	public String generateScript (DataBoard lDataBoard) {
		
		String lCond = mClauseCondition;
		Map<String, String> lLstVars = lDataBoard.getLstVar();
		for (Entry<String, String> le : lLstVars.entrySet()) {
			String lVar = le.getKey();
			String lValue = le.getValue();
			lCond = lCond.replace(lVar, lValue);
			
		}

		return SCRIPT_JS.replace("COND", lCond);
	}
	
	
	public boolean eval (Robot pRobot) throws  ScriptException {
		String lClause = generateScript(pRobot.getRobotData());
		LOGGER.debug("Clause is {}", lClause);
		return ((Boolean)engine.eval(lClause)).booleanValue();
	}
	
	


}
