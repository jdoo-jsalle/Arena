package com.js.dawa.prog.instruction;

import com.js.dawa.iu.arene.ObjetArene;



public interface CmdOnValue {
	
	void init (ObjetArene pObjetArene);
	
	String computeVal (String pVal);

}
