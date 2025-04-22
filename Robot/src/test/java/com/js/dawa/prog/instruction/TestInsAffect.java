package com.js.dawa.prog.instruction;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.BeforeEach;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

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
	
    @ParameterizedTest
     @CsvSource({
        "15, $valeur, 15",
        "15, JS: valeur, 15",//JS:valeur is equivalent to $valeur
        "15, JS:valeur, 15",
        "15, JS: valeur * 2, 30",
        "bidule, $valeur, bidule",
        "bidule, JS: 'valeur' + '_bidule1', bidule_bidule1"
     })
	void testExecInstruction_for_insAffect_from_variable_js_ok (String pValeur,String pVariable, String pAttemp) {
			try {
				//first affect
				InsAffect lInsAffect = new InsAffect();
				lInsAffect.init(mArgs, mRobot, null);
				lInsAffect.execInstruction();
				assertEquals("10", mRobot.getRobotData().getVariable("depla"));
				
				//second affect to valeur
				Args lArgs =new Args (mRobot);
				lArgs.addArguments("valeur");
				lArgs.addArguments(pValeur);
						
				lInsAffect.init(lArgs, mRobot, null);
				lInsAffect.execInstruction();
				assertEquals(pValeur, mRobot.getRobotData().getVariable("valeur"));
		 
				//surcharge by valeur
				 lArgs =new Args (mRobot);
				 lArgs.addArguments("depla");
				 lArgs.addArguments(pVariable);
				 //exec
				 lInsAffect.init(lArgs, mRobot, null);
				 lInsAffect.execInstruction();
				 
				 assertEquals(pAttemp, mRobot.getRobotData().getVariable("depla"));
			
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
