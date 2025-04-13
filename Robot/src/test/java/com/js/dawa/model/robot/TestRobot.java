package com.js.dawa.model.robot;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
	
	@Test
	void test_render() {
		
		RobotsProps lRobotProps = new RobotsProps();
		lRobotProps.setName("?");
		
		Robot lRobot = new Robot();
		lRobot.init(lRobotProps);
		
		assertEquals("?", lRobot.getRender().get(0).getInfoRender().getString());
		
		lRobotProps.setName("N");
		lRobot.reInit();
		
		assertEquals("N", lRobot.getRender().get(0).getInfoRender().getString());
		
	}

}
