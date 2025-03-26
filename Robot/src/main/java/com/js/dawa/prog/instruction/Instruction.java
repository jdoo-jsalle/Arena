package com.js.dawa.prog.instruction;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.Robot;
import com.js.dawa.util.DawaException;


public interface Instruction {
	
	void init(Args pArgsInstruction,Robot pRobot,Arene pArene) throws DawaException;
	
	/**
	 * execute instruction
	 * @return
	 */
	void execInstruction ();
	
	void setFlag (String pVal);
	
	String dump (String pDecal);

}
