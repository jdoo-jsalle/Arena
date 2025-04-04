package com.js.dawa.model.arene;

import com.js.dawa.prog.instruction.Instruction;

public class ModuleArena {
	
	 private ObjetArene mObjetArene;
	 private Instruction mInstruction;
	 
	 
	public ObjetArene getObjetArene() {
		return mObjetArene;
	}
	public void setObjetArene(ObjetArene pObjetArene) {
		this.mObjetArene = pObjetArene;
	}
	public Instruction getInstruction() {
		return mInstruction;
	}
	public void setInstruction(Instruction pInstruction) {
		this.mInstruction = pInstruction;
	}
	
	public boolean isFonctionnel () {
		Energie lEnergie = mObjetArene.getEnergie();
		return lEnergie != null && !lEnergie.isEmpty();
	}

}
