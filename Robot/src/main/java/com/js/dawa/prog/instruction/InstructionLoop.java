package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.ObjetArene;
import com.js.dawa.util.DawaException;

/**
 * condition
 * et itération
 */
public class InstructionLoop implements InstructionLst {
	
	
	private static final Logger LOGGER =  LogManager.getLogger( InstructionLoop.class );

	Args mArgs;
	
	List<Instruction> mLstInstruction = new ArrayList<>();

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArene, Arene pArene) throws DawaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void execInstruction() throws DawaException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setFlag(String pVal) {
		// TODO Auto-generated method stub

	}

	@Override
	public String dump(String pDecal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addInstruction(Instruction pInstruction) {
		mLstInstruction.add(pInstruction);

	}

}
