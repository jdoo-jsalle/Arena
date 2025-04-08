package com.js.dawa;

import java.io.File;

public class ScenarsTest {
	
	static String PATH = "./src/main/resources/tests";
	
	
	void getAreneProperties () {
		File lDir = new File (PATH);
		File [] lLstFile = lDir.listFiles();
		for(File lFile : lLstFile) {
			if (lFile.getName().startsWith("Arene")) {
				String [] lArgs = new String[] {"-D",PATH,"-F",lFile.getName()};
				MainRobot.main(lArgs);
				}
			}
		}
		
		
	
	
	public static void main(String[] args) {
		ScenarsTest lScenarsTest = new ScenarsTest();
		lScenarsTest.getAreneProperties();
		
	}

}
