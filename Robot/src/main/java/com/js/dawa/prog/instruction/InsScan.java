package com.js.dawa.prog.instruction;

import java.util.List;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.arene.ScanerObjet;
import com.js.dawa.util.DawaException;

public class InsScan implements Instruction {
	
	Args mArgsInstruction;
	ObjetArene mObjetArene;
	Arene mArene;
	int mRange= 0;
	ScanerObjet mScaner = new ScanerObjet();

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArena, Arene pArene) throws DawaException {
		mArgsInstruction = pArgsInstruction;
		mObjetArene = pObjetArena;
		mArene = pArene;
		verify();
		mArgsInstruction.setCostInstruction(mRange);//cost instruction prop to range
		mScaner.init(pArene);

	}

	@Override
	public InfoExecIns execInstruction() throws DawaException {
		
		List<ObjetArene>  lLstObjetDetetec = mScaner.detectObjet(mObjetArene, mRange, true);
		mObjetArene.getDataBoard().setListObjetDetected(lLstObjetDetetec);
		
		return new InfoExecIns(this);
	}


	@Override
	public String dump(String pDecal) {
		
		return "Scan : " + Integer.toString(mRange);
	}

	@Override
	public Args getArgs() {
		
		return mArgsInstruction;
	}
	
	void verify () throws DawaException {
		//1:int (range)
		mRange = mArgsInstruction.getArgsInt(0);
	
		
	}

}
