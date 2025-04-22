package com.js.dawa.prog.instruction;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;

public class InsFake implements Instruction {
	Args mArgs;
	
	
	public InsFake () {
		//na
	}
	
	public InsFake (String pKey, String pVal) {
		mArgs=new Args(null);
		mArgs.addArguments(pKey);
		mArgs.addArguments(pVal);
	}

	@Override
	public void init(Args pArgsInstruction, ObjetArene pRobot, Arene pArene) {
		mArgs = pArgsInstruction;

	}

	@Override
	public InfoExecIns execInstruction() {
		return new InfoExecIns(this);

	}

	@Override
	public String dump(String pDecal) {
		
		return pDecal + toString();
	}
	
	@Override
	public String toString () {
		return "Fake " + mArgs.toString();
	}

	@Override
	public Args getArgs() {
		return mArgs;
	}

}
