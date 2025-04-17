package com.js.dawa.prog.instruction;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;

import com.js.dawa.util.DawaException;

public class InsPoursuite implements Instruction {
	
	ObjetArene mRobot;
	Arene mArene;
	Args mArgs;
	DeplaOnOtherObject mDepla;
	

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArena, Arene pArene) throws DawaException {
		mArene = pArene;
		mArgs = pArgsInstruction;
		mRobot = pObjetArena;
		mDepla = new DeplaOnOtherObject(mRobot, mArene);

	}

	@Override
	public InfoExecIns execInstruction() throws DawaException {
		//recupt Last detectes robot
		
		mDepla.follow();
		
		return new InfoExecIns(this);
	}


	@Override
	public String dump(String pDecal) {
		
		return toString();
	}

	@Override
	public Args getArgs() {
	
		return mArgs;
	}
	
	
	public String toString() {
		String lNearestLabel = "<nothing>";
		if (mDepla.getNearest() != null) {
			lNearestLabel = mDepla.getNearest().toString();
		}
		return "Poursuite : nearest :  " + lNearestLabel ;
	}

}
