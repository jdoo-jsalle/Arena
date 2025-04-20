package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;



import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

/**
 * condition
 * et itération
 */
public class InstructionLoop implements InstructionLst {
	
	Args mArgs;
	
	List<Instruction> mLstInstruction = new ArrayList<>();

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArene, Arene pArene) throws DawaException {
		//na

	}

	@Override
	public InfoExecIns execInstruction() throws DawaException {
		return new InfoExecIns(this);

	}


	@Override
	public String dump(String pDecal) {
		return null;
	}

	@Override
	public void addInstruction(Instruction pInstruction) {
		mLstInstruction.add(pInstruction);

	}

	@Override
	public Args getArgs() {
		return mArgs;
	}

}
