package com.js.dawa.prog.instruction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LaunchCmdOnValueRandom {
	
	 private static final Logger LOGGER =  LoggerFactory.getLogger( LaunchCmdOnValueRandom.class );
	
	public static void main(String[] args) {
		
		for(int i =0; i < 10; i++) {
			CmdOnValueRandom l = new CmdOnValueRandom();
			LOGGER.info("===> {}",l.computeVal("rand [2]"));
		}
		
	}

}
