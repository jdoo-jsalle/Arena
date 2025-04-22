package com.js.dawa.prog.parse;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestLignePrg {
	
    @ParameterizedTest
    @CsvSource({
        "truc, truc",
        "truc, truc     ",
        "truc , truc // bidule chouette",
    })
	void test_getValue(String pValue,String pAttempt) {
		LignePrg lLignePrg = new LignePrg();
		assertEquals(pValue, lLignePrg.getValue(pAttempt));
		
	}
    
    @Test
    void test_getValue_is_null () {
    	LignePrg lLignePrg = new LignePrg();
    	assertNull(lLignePrg.getValue(null));
    	
    }
    
    
    @ParameterizedTest
    @CsvSource({
        "  , true",
        "#truc, true     ",
        "truc , false",
        "loop , true",
        "endloop, true"
    })
    void test_verify (String pValue, boolean pAttempt) {
    	LignePrg lLignePrg = new LignePrg();
    	lLignePrg.getValue(pValue);
    	assertEquals(pAttempt, lLignePrg.isSkipable());
    }

	

}
