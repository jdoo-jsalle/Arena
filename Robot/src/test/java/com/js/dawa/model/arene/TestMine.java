package com.js.dawa.model.arene;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.model.robot.Robot;

class TestMine {
	
	
	
	
	@Test
	void test_collision_with_robot () {
		Robot lRobot = new Robot();
		
		
		Mine lMine = new Mine ();
		lMine.mOwner = lRobot;
		
		assertFalse(lMine.isDispose());
		
		lMine.collision(lRobot);
		
		assertTrue(lMine.mTimer != null);

		
		Robot lOtherRobot = new Robot();
		
		lMine.collision(lOtherRobot);
		
	
		assertTrue(lMine.mTimer != null);
	}
	
	@Test
	void test_collision_with_fireball () {
		Robot lRobot = new Robot();
		
		
		Mine lMine = new Mine ();
		lMine.mOwner = lRobot;
		
		assertFalse(lMine.isDispose());
		
		

		FireBall lFireBall = new FireBall ();
		lFireBall.mOwner = lRobot;
		lMine.collision(lFireBall);
		assertFalse(lMine.isDispose());
		
		lMine.collision(lRobot);
		
		assertTrue(lMine.mTimer != null);
		
		
		
		
	
	}
	
	

}
