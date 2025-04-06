package com.js.dawa.prog.instruction;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

public class InsPoursuite implements Instruction {
	
	ObjetArene mObjetArene;
	Arene mArene;
	Args mArgs;

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArena, Arene pArene) throws DawaException {
		mArene = pArene;
		mArgs = pArgsInstruction;
		mObjetArene = pObjetArena;

	}

	@Override
	public InfoExecIns execInstruction() throws DawaException {
		
		
		return new InfoExecIns(this);
	}

	@Override
	public void setFlag(String pVal) {
		//na

	}

	@Override
	public String dump(String pDecal) {
		
		return "Poursuite";
	}

	@Override
	public Args getArgs() {
	
		return mArgs;
	}

}
