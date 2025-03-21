package com.js.dawa.iu.console;

import java.util.Map;

import com.js.dawa.iu.arene.render.CaseAreneRender;

public class ConsoleText implements Console {

	@Override
	public void printRender(CaseAreneRender pCaseRender) {
		printText(pCaseRender.getStringRender());
		
	}

	@Override
	public void rc() {
		printText("\n");
		
	}

	@Override
	public void printText(String pText) {
		System.out.print(pText);
		
	}

	@Override
	public void printHeader(String pDecal,int pTot) {
		printText(pDecal);
		for (int c = 'a'; c <= 'a' + pTot-1; c++) {
			
			printText(Character.toString ((char)c));
	
		}
		rc();
		    
		
	}

	@Override
	public void init(Map<String, String> pParam) {
	   //na
		
	}

}
