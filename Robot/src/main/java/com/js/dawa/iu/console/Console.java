package com.js.dawa.iu.console;

import java.util.Map;

import com.js.dawa.iu.arene.render.CaseAreneRender;

public interface Console {
	
	
	
	void printRender (CaseAreneRender pCaseRender);
	
	void rc ();
	
	void printText (String pText);

	void printHeader (String pDecal,int pTot);
	
	void init (Map<String, String> pParam);
	
}
