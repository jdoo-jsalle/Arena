package com.js.dawa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.commons.cli.ParseException;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class TestMainRobot {
	
	 private static final Logger LOGGER =  LoggerFactory.getLogger( TestMainRobot.class );
	
	@Test
	void test_verifyArgs () {
		
		MainRobot lMainRobot = new MainRobot();
		String [] lArgs = new String[] {"-D","PATH_DIR"};
		try {
			lMainRobot.verifyArgs(lArgs);
			assertEquals("PATH_DIR/", lMainRobot.mDirectory);
		} catch (ParseException e) {
			LOGGER.error("error",e);
		}
		
	}

}
