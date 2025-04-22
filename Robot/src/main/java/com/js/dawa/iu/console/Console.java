package com.js.dawa.iu.console;


import com.js.dawa.model.arene.Arene;

public interface Console {
	
	
	void init (Arene pAren);
	
	void setText (String pText);
	
	void update();//update affichage
	
	void close ();
	
}
