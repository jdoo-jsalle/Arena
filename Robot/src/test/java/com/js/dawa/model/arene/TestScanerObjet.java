package com.js.dawa.model.arene;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import com.js.dawa.model.robot.Position;



public class TestScanerObjet {
	
	
	@Test
	public void test_compareProx_in_out () {
		
		Position lpos1 = new Position(10, 12);
		Position lpos2 = new Position(12, 13);
		
		ScanerObjet lScanerObjet = new ScanerObjet();
		assertFalse(lScanerObjet.compareProx(lpos1, lpos2, 0));
		assertFalse(lScanerObjet.compareProx(lpos1, lpos2, 1));
		assertTrue(lScanerObjet.compareProx(lpos1, lpos2, 2));
		assertTrue(lScanerObjet.compareProx(lpos1, lpos2, 3));
		
		
		
	}

}
