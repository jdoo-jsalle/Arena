package com.js.dawa.prog;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.util.DawaException;

class TestParserCostInstruction {
	

	private static final Logger LOGGER =  LoggerFactory.getLogger( TestParserCostInstruction.class );
	
	@Test
	void test_loadCost() {
		ParserCostInstruction lParser = new ParserCostInstruction("./src/test/resources/conf.test/");
		try {
			lParser.loadCost(null);
			assertEquals(20,lParser.mCostInstruction.getLstCostInst().get("invisible").intValue());
			assertEquals(3,lParser.mCostInstruction.getLstCostInst().get("tir").intValue());
			
			lParser.loadCost("cost_instruction.properties");
			assertEquals(100,lParser.mCostInstruction.getLstCostInst().get("invisible").intValue());
			assertEquals(3,lParser.mCostInstruction.getLstCostInst().get("tir").intValue());
		} catch (DawaException e) {
			LOGGER.error("error",e);
			assertFalse(true);
		}
		
		
	}
	

}
