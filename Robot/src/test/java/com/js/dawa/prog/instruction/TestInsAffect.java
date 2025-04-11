package com.js.dawa.prog.instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;

import com.js.dawa.model.robot.Robot;
import com.js.dawa.util.DawaException;

class TestInsAffect {
	
	 private static final Logger LOGGER =  LoggerFactory.getLogger( TestInsAffect.class );
	 
	 Robot mRobot;
	 Args mArgs;
	 
	 @BeforeEach
	 void setupEach() {
		     
	     mRobot = new Robot();
			
		 mArgs = new Args(mRobot);
		 mArgs.addArguments("depla");
		 mArgs.addArguments("10");
	     
	 }
	
	@Test
	void testExecInstruction_for_insAffect () {
		InsAffect lInsAffect = new InsAffect();
		
		try {
					
			lInsAffect.init(mArgs, mRobot, null);
			lInsAffect.execInstruction();
			
			assertEquals("10", mRobot.getRobotData().getVariable("depla"));
			
		} catch (DawaException e) {
			
			LOGGER.error("error",e);
		}
		
		
	}
	
	@Test
	void testExecInstruction_for_insAffect_from_variable () {
			try {
				//first affect
				InsAffect lInsAffect = new InsAffect();
				lInsAffect.init(mArgs, mRobot, null);
				lInsAffect.execInstruction();
				assertEquals("10", mRobot.getRobotData().getVariable("depla"));
				
				//second affect to valeur
				Args lArgs =new Args (mRobot);
				lArgs.addArguments("valeur");
				lArgs.addArguments("15");
						
				lInsAffect.init(lArgs, mRobot, null);
				lInsAffect.execInstruction();
				assertEquals("15", mRobot.getRobotData().getVariable("valeur"));
		 
				//surcharge by valeur
				 lArgs =new Args (mRobot);
				 lArgs.addArguments("depla");
				 lArgs.addArguments("$valeur");
				 //exec
				 lInsAffect.init(lArgs, mRobot, null);
				 lInsAffect.execInstruction();
				 
				 assertEquals("15", mRobot.getRobotData().getVariable("depla"));
			
			} catch (DawaException e) {
				
				LOGGER.error("error",e);
			}
		
		
		
	}
	
	@Test
	void testExecInstruction_for_insAffect_from_variable_not_existent () {
		try {
			//first affect
			InsAffect lInsAffect = new InsAffect();
			lInsAffect.init(mArgs, mRobot, null);
			lInsAffect.execInstruction();
			assertEquals("10", mRobot.getRobotData().getVariable("depla"));
			
			
			//surcharge by valeur, not existents
			 Args lArgs =new Args (mRobot);
			 lArgs.addArguments("depla");
			 lArgs.addArguments("$valeur");
			 //exec
			 lInsAffect.init(lArgs, mRobot, null);
			 lInsAffect.execInstruction();
			 
			 assertEquals(null, mRobot.getRobotData().getVariable("depla"));
		
		} catch (DawaException e) {
			
			LOGGER.error("error",e);
		}
	
		
		
	}


}
