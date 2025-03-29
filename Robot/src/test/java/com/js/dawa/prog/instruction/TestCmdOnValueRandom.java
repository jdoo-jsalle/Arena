package com.js.dawa.prog.instruction;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestCmdOnValueRandom {
	
	@Test
	public void test_getArgsRandom () {
		
		CmdOnValueRandom lCmdRandom = new CmdOnValueRandom();
		assertEquals(10,lCmdRandom.getArgsRandom("Rand [ 10 ]"));
		
		
		
		
	}
	
	
	@Test
	public void test_computeVal () {
		
		CmdOnValueRandom lCmdRandom = new CmdOnValueRandom();
		String lVal =  lCmdRandom.computeVal("Rand [ 10 ]");
		int lRes = Integer.parseInt(lVal);
		assertTrue(lRes >= 0 && lRes < 10);
	}

}
