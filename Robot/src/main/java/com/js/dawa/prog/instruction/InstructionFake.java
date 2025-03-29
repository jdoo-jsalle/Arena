package com.js.dawa.prog.instruction;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.ObjetArene;

public class InstructionFake implements Instruction {
	Args mArgs;

	@Override
	public void init(Args pArgsInstruction, ObjetArene pRobot, Arene pArene) {
		mArgs = pArgsInstruction;

	}

	@Override
	public void execInstruction() {
		//fake

	}

	@Override
	public void setFlag(String pVal) {
		//fake

	}

	@Override
	public String dump(String pDecal) {
		
		return mArgs.toString();
	}
	
	@Override
	public String toString () {
		return mArgs.toString();
	}

}
