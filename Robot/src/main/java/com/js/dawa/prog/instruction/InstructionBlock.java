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
	
	InfoExecIns mRes;
	

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArene, Arene pArene) {
		mArgs = pArgsInstruction;
		
	}


	@Override
	public InfoExecIns execInstruction() throws DawaException{
		//exec step by step
		if (mStep == 0) {
			mRes = new InfoExecIns();
		}
		if (!mLstInstruction.isEmpty()) {//no instruction
			Instruction lNext = mLstInstruction.get(mStep);
			LOGGER.debug("=> {}" , lNext);
		
			InfoExecIns lResFils =   lNext.execInstruction();
			if (lResFils.isOver()) {
			    mStep++;
				if (mStep >= mLstInstruction.size()) {
					mStep = 0;
					mRes.setOver(true);
				}
				else {
					mRes.setOver(false);
				}
			}
			else {
				mRes.setOver(false);
			}
			
		}
		
		return mRes;
		
		
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
		StringBuilder lRes = new StringBuilder();
		if (mArgs != null) {
			lRes.append(mArgs.toString());
		}
		else {
			lRes.append("<args empty>");
		}
		lRes.append("\n");
		
		for (Instruction lInstruction : mLstInstruction) {
			String lDump =  lInstruction.dump(pDecal);
			LOGGER.info("{} {}",pDecal, lDump);
			lRes.append(pDecal);
			lRes.append(lInstruction.dump(pDecal));
			lRes.append("\n");
		}
		
		
		return lRes.toString();
	}
	
	public Args getArgs() {
		return mArgs;
	}
	
	
	
	
	
}
