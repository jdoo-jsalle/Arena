package com.js.dawa.model.robot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestPosition {
	
	@Test
	void getVector_zero() {
		
		Position lPos1 = new Position (0,0);
		Position lPos2 = new Position (0,0);
		
		Position lRes = lPos1.getVector(lPos2);
		
		assertEquals(0, lRes.getX());
		assertEquals(0, lRes.getY());
		
		
	}
	
	@Test
	void getVector__diff_zero_10_0() {
		
		Position lPos1 = new Position (10,0);
		Position lPos2 = new Position (0,0);
		
		Position lRes = lPos1.getVector(lPos2);
		
		assertEquals(-1, lRes.getX());
		assertEquals(0, lRes.getY());
		
		
	}
	
	@Test
	void getVector__diff_zero_0_10() {
		
		Position lPos1 = new Position (0,0);
		Position lPos2 = new Position (10,0);
		
		Position lRes = lPos1.getVector(lPos2);
		
		assertEquals(1, lRes.getX());
		assertEquals(0, lRes.getY());
		
		
	}

	
	@Test
	void getVector__diff_zero_5_10() {
		
		Position lPos1 = new Position (5,10);
		Position lPos2 = new Position (10,5);
		
		Position lRes = lPos1.getVector(lPos2);
		
		assertEquals(1, lRes.getX());
		assertEquals(-1, lRes.getY());
		
		
	}
	
	@Test
	void getDistance () {
		Position lPos1 = new Position (5,10);
		Position lPos2 = new Position (8,14);
		
		assertEquals(5, lPos1.distance(lPos2));
	}
}
