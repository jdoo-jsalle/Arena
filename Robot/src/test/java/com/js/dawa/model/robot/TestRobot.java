package com.js.dawa.model.robot;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

class TestRobot {
	
	@Test
	void test_visible() {
		RobotsProps lRobotProps = new RobotsProps();
		lRobotProps.setVisibilte(true);
		
		assertTrue(lRobotProps.isVisibilite());
		
		Robot lRobot = new Robot();
		lRobot.init(lRobotProps);
		
		assertTrue (lRobot.isVisible());
	}

}
