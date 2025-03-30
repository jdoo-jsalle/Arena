package com.js.dawa.prog.instruction;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;


public interface Instruction {
	
	void init(Args pArgsInstruction,ObjetArene pObjetArena,Arene pArene) throws DawaException;
	
	/**
	 * execute instruction
	 * @return
	 */
	void execInstruction () throws DawaException;
	
	void setFlag (String pVal);
	
	String dump (String pDecal);

}
