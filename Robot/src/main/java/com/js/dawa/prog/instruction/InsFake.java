package com.js.dawa.prog.instruction;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;

public class InsFake implements Instruction {
	Args mArgs;

	@Override
	public void init(Args pArgsInstruction, ObjetArene pRobot, Arene pArene) {
		mArgs = pArgsInstruction;

	}

	@Override
	public InfoExecIns execInstruction() {
		return new InfoExecIns();

	}

	@Override
	public void setFlag(String pVal) {
		//fake

	}

	@Override
	public String dump(String pDecal) {
		
		return pDecal + toString();
	}
	
	@Override
	public String toString () {
		return "Fake " + mArgs.toString();
	}

}
