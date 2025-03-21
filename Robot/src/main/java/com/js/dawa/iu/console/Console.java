package com.js.dawa.iu.console;

import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;

public interface Console {
	

	
	void printRender (CaseRender pCaseRender);
	
	void rc ();
	
	void printText (String pText);

	
	void init (Map<String, String> pParam);
	
}
