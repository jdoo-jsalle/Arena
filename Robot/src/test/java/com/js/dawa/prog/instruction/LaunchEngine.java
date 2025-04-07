package com.js.dawa.prog.instruction;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchEngine {
	
	 private static final Logger LOGGER =  LoggerFactory.getLogger( LaunchEngine.class );
	
	 
	 
	 void testCompute () {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("graal.js");
		String lClause ="function compute (){"
				+ "    return 3 * -4;" 
				+ "}"
				+ "compute();";
		
		try {
			Object lVal = engine.eval(lClause);
			LOGGER.info("res {}",lVal);
		} catch (ScriptException e) {
			LOGGER.error("error",e);
		
		}
	 }
	 
	 
	 
	void testCond() {
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
	
	public static void main(String[] args) {
		LaunchEngine lLaunchEngine = new LaunchEngine();
		lLaunchEngine.testCompute();
		lLaunchEngine.testCond();
	}
	
	

}
