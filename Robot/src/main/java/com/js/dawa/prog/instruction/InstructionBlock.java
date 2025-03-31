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
	
	static int mIdBlockTot = 0;
	
	static void addIdBlockTot() {
		mIdBlockTot ++;
	}
	
	public static void reinitIdBlock() {
		mIdBlockTot = 0;
	}
	
	int mIdBlock;
	
	
	public InstructionBlock(){
		mIdBlock = mIdBlockTot;
		addIdBlockTot();
	}

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArene, Arene pArene) {
		//na
		
	}


	@Override
	public InfoExecIns execInstruction() throws DawaException{
		LOGGER.debug("========= Begin Block {} =====================",mIdBlock);
		//exec step by step
		if (mStep == 0) {
			mRes = new InfoExecIns(this);
		}
		if (!mLstInstruction.isEmpty()) {//no instruction
			Instruction lNext = mLstInstruction.get(mStep);
			LOGGER.debug("===> Next :  {} for Block {}" , lNext, mIdBlock);
			
		
			InfoExecIns lResFils =   lNext.execInstruction();
			
			LOGGER.debug("==> add ResFils of \"{}\" in Block \"{}\"",lNext.toString(), mIdBlock);
			mRes.addInfoExecIns(lResFils);
			LOGGER.debug("==> Res : {}" ,  mRes.dump());
			
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
		
		LOGGER.debug("========= End Block {} =====================",mIdBlock);
		
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
