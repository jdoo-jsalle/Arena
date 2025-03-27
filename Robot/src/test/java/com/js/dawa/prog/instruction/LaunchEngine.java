package com.js.dawa.prog.instruction;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LaunchEngine {
	
	 private static final Logger LOGGER =  LogManager.getLogger( LaunchEngine.class );
	
	public static void main(String[] args) {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("graal.js");
		
		String lClause ="function condition (truc,bidule){"
				+ "if (truc > 5 && bidule < 3){"
				+ "   return true;"
				+ "}"
				+ " else {"
				+ "   return false;"
				+ "}"
				+ "}"
				+ "condition (10,2);";
		
		
		try {
			Object lVal = engine.eval(lClause);
			LOGGER.info("res {}",lVal);
		} catch (ScriptException e) {
			LOGGER.error("error",e);
		
		}
		
	}
	
	

}
