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
	
	ObjetArene mObjetArene;
	
	
	
	int mIdBlock;
	
	
	public InstructionBlock(){
		mIdBlock = IdBlock.getIdBlock();
		
	
	}
	
	public InstructionBlock(int pIdBlock){
		mIdBlock =pIdBlock;
	
	}

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArene, Arene pArene) {
		mArgs = pArgsInstruction;
		mObjetArene = pObjetArene;
		//na
		
	}

	/**
	 * Execute all instruction without step
	 * @throws DawaException
	 */
	public void execAllInstruction () throws DawaException {
		for (Instruction lInstruction : mLstInstruction){
			lInstruction.execInstruction();
		}
	}

	@Override
	public InfoExecIns execInstruction() throws DawaException{
					
		LOGGER.debug("========= Begin Block {} =====================",mIdBlock);
		//exec step by step
		if (mStep == 0) {
			mRes = new InfoExecIns(this);
			if (mArgs != null) {//deduct energie fro Block
				mArgs.deductCostToObjectArena();
			}
		}
		execInstructionStepByStep();
		
		LOGGER.debug("========= End Block {} =====================",mIdBlock);
		
		return mRes;
		
		
	}
	
	
	void execInstructionStepByStep () throws DawaException{
		if (!mLstInstruction.isEmpty()) {//no instruction
			Instruction lNext = mLstInstruction.get(mStep);
			LOGGER.debug("===> Next :  {} for Block {}" , lNext, mIdBlock);
			
			//exec instruction found, get resutat in lResFils
			InfoExecIns lResFils =   lNext.execInstruction();
			
			//Deduct energie fro last instruction
			Instruction lLastExec = lResFils.getLastInfoExec().getInstruction();
			Args lArgsLastExec = lLastExec.getArgs();
			if (lArgsLastExec != null) {
				lArgsLastExec.deductCostToObjectArena();
			}
			
			mRes.addInfoExecIns(lResFils);
			if (LOGGER.isDebugEnabled()) {LOGGER.debug("==> Res : {}" ,  mRes);}
			
			//Compute flag over
			computeFlagOver(lResFils);
			
		}
	}
	
	/**
	 * 
	 * @param pInfoExecIns
	 */
	void computeFlagOver (InfoExecIns pInfoExecIns) {
		//Compute flag over
		if (pInfoExecIns.isOver()) {//block over
			
		    mStep++;//increment instruction for ths block
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
		lRes.append(toString());
		lRes.append("\n");
		
		for (Instruction lInstruction : mLstInstruction) {
			lRes.append(pDecal);
			lRes.append(lInstruction.dump(pDecal));
			lRes.append("\n");
		}
		
		
		return lRes.toString();
	}
	
	public Args getArgs() {
		
		return mArgs;
	}
	
	@Override
	public String toString() {
		StringBuilder lRes = new StringBuilder("Block[" + Integer.toString(mIdBlock) + "]:");
		if (mArgs != null) {
			lRes.append(mArgs.toString());
		}
		else {
			lRes.append("<args empty>");
		}
		return lRes.toString();
	}
	
	
	
	
}
