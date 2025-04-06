package com.js.dawa.model.arene;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;


import org.junit.jupiter.api.Test;

import com.js.dawa.model.robot.Position;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.model.robot.RobotsProps;



class TestScanerObjet {
	

	
	
	@Test
	void test_compareProx_in_out () {
		
		Position lpos1 = new Position(10, 12);
		Position lpos2 = new Position(12, 13);
		
		ScanerObjet lScanerObjet = new ScanerObjet();
		assertFalse(lScanerObjet.compareProx(lpos1, lpos2, 0));
		assertFalse(lScanerObjet.compareProx(lpos1, lpos2, 1));
		assertTrue(lScanerObjet.compareProx(lpos1, lpos2, 2));
		assertTrue(lScanerObjet.compareProx(lpos1, lpos2, 3));
		
		
		
	}
	
	
	@Test
	void test_detectObjet_empty_list_objectArena () {
		Arene lArene = new Arene(null);
		
		ScanerObjet lScanerObjet = new ScanerObjet();
		lScanerObjet.init(lArene);
		Robot lRobot = new Robot();
		lRobot.setPosition(new Position (10,10));
		
		List<ObjetArene> lRes = lScanerObjet.detectObjet(lRobot, 2, false);
		assertTrue(lRes.isEmpty());
	}
	
	@Test
	void test_detectObjet_one_objet_out_of_range_in_range_visible_and_not_visible () {
		Arene lArene = new Arene(null);
		
		
		CaseArene lCaseArene = new CaseArene();
		lCaseArene.setPosition(new Position (20,20));
		
		//Create Module
		ModuleArena lModule = new ModuleArena();
		lModule.setObjetArene(lCaseArene);
		lModule.setIsRobot();
		
		//add Module to Arena
		lArene.addObjetArene(lModule);
		
		//create Module Robot
		Robot lRobot = new Robot();
		lRobot.setPosition(new Position (10,10));
		ModuleArena lModuleRobot = new ModuleArena();
		lModuleRobot.setObjetArene(lRobot);
		lModuleRobot.setIsRobot();
		
		//add Robot to Arena
		lArene.addObjetArene(lModuleRobot);
		
		//test Scanner
		ScanerObjet lScanerObjet = new ScanerObjet();
		lScanerObjet.init(lArene);
		
		
		//first scan to 2
		List<ObjetArene> lRes = lScanerObjet.detectObjet(lRobot, 2, false);
		assertTrue(lRes.isEmpty());
		//second scan to 10 (all object)
		lRes = lScanerObjet.detectObjet(lRobot, 10, false);
		assertEquals(lCaseArene, lRes.get(0));
		
		
		
		//second scan to 10 (all object) and objet invisible
		lCaseArene.setVisible(false);
		lRes = lScanerObjet.detectObjet(lRobot, 10, false);
		assertTrue(lRes.isEmpty());
		
		
		
	}
	
	@Test
	void detect_only_robot () {
		Arene lArene = new Arene(null);
		
		
		CaseArene lCaseArene = new CaseArene();
		lCaseArene.setPosition(new Position (20,20));
		
		//Create Module for Case
		ModuleArena lModule = new ModuleArena();
		lModule.setObjetArene(lCaseArene);

		
		//add Module to Arena
		lArene.addObjetArene(lModule);
		
		//create Module Robot
		Robot lRobot = new Robot();
		lRobot.setPosition(new Position (10,10));
		ModuleArena lModuleRobot = new ModuleArena();
		lModuleRobot.setObjetArene(lRobot);
		lModuleRobot.setIsRobot();
		
		//add Robot to Arena
		lArene.addObjetArene(lModuleRobot);
		
		//create another Robot
		//create Module Robot
		Robot lRobot2 = new Robot();
		lRobot2.setPosition(new Position (15,15));
		lModuleRobot = new ModuleArena();
		lModuleRobot.setObjetArene(lRobot2);
		lModuleRobot.setIsRobot();
		
		RobotsProps lRobotProps = new RobotsProps();
		lRobotProps.setVisibilte(true);
		lRobot2.init(lRobotProps);
		
		//add Robot to Arena
		lArene.addObjetArene(lModuleRobot);

		
		
		//test Scanner
		ScanerObjet lScanerObjet = new ScanerObjet();
		lScanerObjet.init(lArene);
		
		//detect all objet
		List<ObjetArene> lRes = lScanerObjet.detectObjet(lRobot, 10, false);
		assertEquals(2, lRes.size());
		
		
		//detect only objet
		lRes = lScanerObjet.detectObjet(lRobot, 10, true);
		assertEquals(1, lRes.size());
		assertEquals(lRobot2, lRes.get(0));
		
		
		
		
		
	}

}
