package com.js.dawa.model.position;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TestAxe {
	
	  @ParameterizedTest
	     @CsvSource({
	        "0,1, 1",
	        "0,-1, 7",
	        "0,10, 2",
	        "5,1,6",
	        "5,8,5",
	        "5,-8,5",
	        "5,-1,4",
	        "5,9,6"
        
	     })
	void testDoRotation (int pDir,int pRot, int pAttemps) {
		  Axe lAxe = new Axe();
		  lAxe.setDir(pDir);
		  lAxe.doRotation(pRot);
		  assertEquals(pAttemps,lAxe.getDir());
		
	}
	  
	  @ParameterizedTest
	     @CsvSource({
	        "0,0",
	        "1,45",
	        "2,90",
	        "3,135",
	        "4,180",
	        "5,225",
	        "6,270",
	        "7,315",
	        "8,0"
     
	     })
	void testConvertDegre_huit (int pDir, int pDegre) {
		  Axe lAxe = new Axe();
		  lAxe.setDir(pDir);
		  assertEquals(pDegre, lAxe.convertDegree());
		  
	  }
	  
	  @ParameterizedTest
	     @CsvSource({
	        "0,0",
	        "1,60",
	        "2,120",
	        "3,180",
	        "4,240",
	        "5,300",
	        "6,0"
	       
  
	     })
	void testConvertDegre_six (int pDir, int pDegre) {
		  Axe lAxe = new Axe(6);
		  lAxe.setDir(pDir);
		  assertEquals(pDegre, lAxe.convertDegree());
		  
	  }

}
