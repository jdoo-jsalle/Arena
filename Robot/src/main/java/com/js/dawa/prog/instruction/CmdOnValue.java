package com.js.dawa.prog.instruction;

import com.js.dawa.iu.arene.ObjetArene;



public interface CmdOnValue {
	
	void init (ObjetArene pRobot);
	
	String computeVal (String pVal);

}
