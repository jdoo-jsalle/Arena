package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

public class InstructionBlock implements InstructionLst {
	
	private static final Logger LOGGER =  LogManager.getLogger( InstructionBlock.class );

	Args mArgs;
	
	List<Instruction> mLstInstruction = new ArrayList<>();
	
	int mStep =0;
	

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArene, Arene pArene) {
		mArgs = pArgsInstruction;
		
	}


	@Override
	public void execInstruction() throws DawaException{
		//exec step by step
		if (mLstInstruction.size() >0) {//no instruction
			if (mStep >= mLstInstruction.size()) {
				mStep = 0;
			}
			Instruction lNext = mLstInstruction.get(mStep);
			
		
			lNext.execInstruction();
			mStep++;
		}
		
		
	}


	@Override
	public void addInstruction(Instruction pInstruction) {
		mLstInstruction.add(pInstruction);
		
	}


	@Override
	public void setFlag(String pVal) {
		//na
		
	}


	@Override
	public String dump(String pDecal) {
		for (Instruction lInstruction : mLstInstruction) {
			LOGGER.info("{} {}",pDecal, lInstruction.dump(pDecal));
		}
		
		return mArgs.toString();
	}
	
	
	
	
	
}
