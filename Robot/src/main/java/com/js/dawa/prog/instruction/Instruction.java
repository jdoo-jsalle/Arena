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
	default void setFlag (String pVal) {
		//na
	}
	
	String dump (String pDecal);
	
	Args getArgs ();

	
	default boolean isOver () {
		return true;
	}
	
	default int getTotLoop () {
		return 1;
	}
	
	/**
	 * return true, if list of instruction
	 * @param pInstruction
	 * @return
	 */
	default boolean replaceInstructionCurrent (Instruction pInstruction) {
		return true;
	}
	
	
	default void setEmergencyInstruction(Instruction pInstruction) {
		//na
	}

	
}
