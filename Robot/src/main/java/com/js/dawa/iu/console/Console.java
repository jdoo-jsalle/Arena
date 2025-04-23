package com.js.dawa.iu.console;


import com.js.dawa.model.arene.Arene;

public interface Console {
	
	
	void init (Arene pAren);
	
	void setText (String pText);
	
	void close ();
	
	void start ();
	
	void end();
	
}
