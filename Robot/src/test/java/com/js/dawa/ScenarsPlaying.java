package com.js.dawa;

import java.io.File;

public class ScenarsPlaying {
	
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
		ScenarsPlaying lScenarsTest = new ScenarsPlaying();
		lScenarsTest.getAreneProperties();
		
	}

}
