package com.js.dawa;

public class MainRobotLaunch {
	
	
	public static void main(String[] args) {
		
		
		
		//String [] lArgs = new String [] {"-D", "./src/main/resources/"};
		//String [] lArgs = new String [] {"-D", "./src/main/resources/tests/","-F","Arene_tir_own_mine.properties"};
		String [] lArgs = new String [] {"-D", "./src/main/resources/tests/","-F","Arene_mine2.properties"};
		//String [] lArgs = new String [] {"-D", "./src/main/resources/tests/","-F","Arene_Fake.properties"};
		//String [] lArgs = new String [] {"-D", "./src/main/resources/tests/","-F","Arene_init.properties"};
		MainRobot.main(lArgs);
	}

}
