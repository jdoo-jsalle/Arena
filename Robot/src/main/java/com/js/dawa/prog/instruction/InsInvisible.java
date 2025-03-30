package com.js.dawa.prog.instruction;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

public class InsInvisible implements Instruction {
	
	Args mArgs;
	int mX =0;
	ObjetArene mObjetArene;
	Arene mArene;

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArena, Arene pArene) throws DawaException {
		mArene = pArene;
		mArgs = pArgsInstruction;
		mObjetArene = pObjetArena;
		
	}

	@Override
	public InfoExecIns execInstruction() throws DawaException {
		mObjetArene.setVisible(false);
		return new InfoExecIns();
		
	}

	@Override
	public void setFlag(String pVal) {
		//na
		
	}

	@Override
	public String dump(String pDecal) {
		return pDecal + toString();
	}
	
	public String toString () {
		return "Invisible " + mArgs.toString();
	}

}
