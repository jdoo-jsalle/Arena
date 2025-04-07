package com.js.dawa.prog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestParserAreneProps {
	
	@Test
	void test_getProperties () {
		ParserAreneProps lParser = new ParserAreneProps();
		assertEquals("10000",lParser.getProperties("Arene.energie:10000"));
	}

}
