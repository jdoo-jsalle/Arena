package com.js.dawa;

public class MainRobotLaunch {
	
	
	public static void main(String[] args) {
		//String [] lArgs = new String [] {"-D", "./src/main/resources/"};
		String [] lArgs = new String [] {"-D", "./src/main/resources/tests/","-F","Arene_tir_own_mine.properties"};
		MainRobot.main(lArgs);
	}

}
