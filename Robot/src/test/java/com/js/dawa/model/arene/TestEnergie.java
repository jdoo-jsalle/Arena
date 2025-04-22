package com.js.dawa.model.arene;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestEnergie {
	
	
	@Test
	void test_averageDamageByTurn () {
		Energie lE = new Energie();
		lE.addDamage(10);
		lE.addDamage(15);
		lE.addDamage(5);
		
		assertEquals(10, lE.averageDamageByTurn(3));
		
	}
	
	@Test
	void test_averageSpendByTurn () {
		Energie lE = new Energie();
		lE.addDamage(10);
		lE.addDamage(15);
		lE.addDamage(5);
		lE.add(-10);
		lE.add(500);
		
		assertEquals(10, lE.averageSpendByTurn(4));
		
	}
	

}
