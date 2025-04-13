package com.js.dawa.prog.instruction;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

/*
 * Defined a statement that will be executed.
 */
public interface Instruction {
	
	
	/**
	 * Init the instruction
	 * @param pArgsInstruction
	 * @param pObjetArena
	 * @param pArene
	 * @throws DawaException
	 */
	void init(Args pArgsInstruction,ObjetArene pObjetArena,Arene pArene) throws DawaException;
	
	/**
	 * execute instruction
	 * @return
	 */
	InfoExecIns execInstruction () throws DawaException;
	
	/**
	 * for internal manage.
	 * @param pVal
	 */
	void setFlag (String pVal);
	
	String dump (String pDecal);
	
	Args getArgs ();

	
	default boolean isOver () {
		return true;
	}
	
}
