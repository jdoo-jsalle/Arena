package com.js.dawa.model.position;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TestWayOfPosition {
	
	
	
	@Test
	void testGetDistanceForTheDuration () {
		WayOfPositionSquare lWayOfPosition = new WayOfPositionSquare();
		assertEquals(0.05, lWayOfPosition.getDistanceForTheDuration(50));
	}
	
	
	@Test
	void testComputeNext_threePosition () {
		
		WayOfPositionSquare lWayOfPosition = new WayOfPositionSquare();
		Position lPosition1 = new Position (0,0);
		lWayOfPosition.addPosition(lPosition1);
		Position lPosition2 = new Position (100,0);
		lWayOfPosition.addPosition(lPosition2);
		Position lPosition3 = new Position (200,0);
		lWayOfPosition.addPosition(lPosition3);
		
		Position lres = lWayOfPosition.computeNext(50);//duration 50 miliseconde later
		assertEquals(5, lres.getXi());
		assertEquals(0, lres.getYi());
		
		lres = lWayOfPosition.computeNext(100);
		
		assertEquals(10, lres.getXi());
		assertEquals(0, lres.getYi());
		
		lres = lWayOfPosition.computeNext(150);
		
		assertEquals(15, lres.getXi());
		assertEquals(0, lres.getYi());
		assertEquals(3, lWayOfPosition.getTotPos());
		lres = lWayOfPosition.computeNext(200);
		
		assertEquals(3, lWayOfPosition.getTotPos());
		
		assertEquals(20, lres.getXi());
		assertEquals(0, lres.getYi());
		
		assertEquals(3, lWayOfPosition.getTotPos());
		
	    lres = lWayOfPosition.computeNext(1000);
		
		assertEquals(100, lres.getXi());
		assertEquals(0, lres.getYi());
		
		assertEquals(2, lWayOfPosition.getTotPos());
		
		
	}

}
