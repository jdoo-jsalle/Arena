package com.js.dawa;

import java.util.HashMap;
import java.util.Map;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.CaseArene;
import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.iu.console.Console;
import com.js.dawa.iu.console.ConsoleGraphique;


public class Launcher {
	
	
	
	public static void main(String[] args) {
		Console lConsole = new ConsoleGraphique();
		Map<String, String> lParams = new HashMap<String, String>();
		lParams.put("titre", "Arene");
		lConsole.init(lParams);
		Arene lArene = new Arene(lConsole);
		
		CaseArene lCaseArene = new CaseArene();
		lCaseArene.setCaseAreneRender(new CaseAreneRenderDefaut());
		
		lArene.addCaseArene(10, 10, lCaseArene);
		
		
		lArene.printArene();
	}

}
