package com.js.dawa.model.arene;

import com.js.dawa.prog.instruction.Instruction;
import com.js.dawa.util.DawaException;

public class ModuleArena {
	
	 
	 private ObjetArene mObjetArene;
	 private String mNamePrg;
	 private Instruction mInstructionLoop;
	 private Instruction mInstructionInit;
	 
	 private boolean mIsRobot = false;
	 
	 
	public ObjetArene getObjetArene() {
		return mObjetArene;
	}
	public void setObjetArene(ObjetArene pObjetArene) {
		this.mObjetArene = pObjetArene;
	}
	public Instruction getInstructionLoop() {
		return mInstructionLoop;
	}
	public void setInstructionLoop(Instruction pInstructionLoop) {
		this.mInstructionLoop = pInstructionLoop;
	}
	
	public boolean isFonctionnel () {
		Energie lEnergie = mObjetArene.getEnergie();
		return lEnergie != null && !lEnergie.isEmpty();
	}
	public Instruction getInstructionInit() {
		return mInstructionInit;
	}
	public void setInstructionInit(Instruction pInstructionInit) {
		this.mInstructionInit = pInstructionInit;
	}
	
	public void init () throws DawaException {
		if (mInstructionInit != null) {
			mInstructionInit.execInstruction();
		}
	}
	
	
	public boolean isRobot() {
		return mIsRobot;
	}
	public void setIsRobot() {
		this.mIsRobot = true;
	}
	

	public String getNamePrg() {
		return mNamePrg;
	}
	public void setNamePrg(String pNamePrg) {
		this.mNamePrg = pNamePrg;
	}
	
	@Override
	public String toString() {
		return mObjetArene.toString();
	}

	

	
	

}
