package com.js.dawa.prog.instruction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LaunchCmdOnValueRandom {
	
	 private static final Logger LOGGER =  LogManager.getLogger( LaunchCmdOnValueRandom.class );
	
	public static void main(String[] args) {
		
		for(int i =0; i < 10; i++) {
			CmdOnValueRandom l = new CmdOnValueRandom();
			LOGGER.info("===> {}",l.computeVal("rand [2]"));
		}
		
	}

}
