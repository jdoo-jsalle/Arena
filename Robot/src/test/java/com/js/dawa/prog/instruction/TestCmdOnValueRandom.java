package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;

class TestCmdOnValueRandom {
	 private static final Logger LOGGER =  LoggerFactory.getLogger( TestCmdOnValueRandom.class );
	
	@Test
	void test_getArgsRandom () {
		
		CmdOnValueRandom lCmdRandom = new CmdOnValueRandom();
		assertEquals(10,lCmdRandom.getArgsRandom("Rand [ 10 ]"));
		
		
		
		
	}
	
	
	@Test
	void test_computeVal () {
		
		CmdOnValueRandom lCmdRandom = new CmdOnValueRandom();
		String lVal =  lCmdRandom.computeVal("Rand [ 10 ]");
		int lRes = Integer.parseInt(lVal);
		LOGGER.debug("val rand {}", lRes);
		assertTrue(lRes >= -10 && lRes < 10);
	}

}
