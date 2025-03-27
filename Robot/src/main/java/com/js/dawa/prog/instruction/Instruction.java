package com.js.dawa.prog.instruction;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.robot.model.Robot;
import com.js.dawa.util.DawaException;


public interface Instruction {
	
	void init(Args pArgsInstruction,Robot pRobot,Arene pArene) throws DawaException;
	
	/**
	 * execute instruction
	 * @return
	 */
	void execInstruction () throws DawaException;
	
	void setFlag (String pVal);
	
	String dump (String pDecal);

}
