package com.js.dawa;

public class MainRobotLaunch {
	
	
	public static void main(String[] args) {
		
		
		String [][] lArgs = new String [][] {
			 new String [] {"-D", "./src/exemple/"},
			 new String [] {"-D", "./src/exemple/tests/","-F","Arene_tir_own_mine.properties"},
			 new String [] {"-D", "./src/exemple/tests/","-F","Arene_mine.properties"},
			 new String [] {"-D", "./src/exemple/tests/","-F","Arene_mine2.properties"},
			 new String [] {"-D", "./src/exemple/tests/","-F","Arene_Fake.properties"},
			 new String [] {"-D", "./src/exemple/tests/","-F","Arene_init.properties"}
			 
		};
		
		
		MainRobot.main(lArgs[0]);
	}

}
