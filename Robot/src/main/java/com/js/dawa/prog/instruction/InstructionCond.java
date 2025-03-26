package com.js.dawa.prog.instruction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.Robot;

public class InstructionCond implements InstructionLst {
	
	private static final Logger LOGGER =  LogManager.getLogger( InstructionCond.class );
	
	InstructionBlock mLstIf = new InstructionBlock();
	InstructionBlock mLstElse = new InstructionBlock();
	
	String mFlag;
	
	Args mArgs;

	@Override
	public void init(Args pArgsInstruction, Robot pRobot, Arene pArene) {
		mArgs = pArgsInstruction;

	}
	
	
	
	public void addInstructionIf (Instruction pInstruction) {
		mLstIf.addInstruction(pInstruction);
	}
	
	public void addInstructionElse(Instruction pInstruction) {
		mLstElse.addInstruction(pInstruction);
	}

	@Override
	public void execInstruction() {
		if (execCondition()) {
			mLstIf.execInstruction();
		}
		else {
			mLstElse.execInstruction();
		}

	}
	
	
	boolean execCondition() {
		return false;
	}



	@Override
	public void addInstruction(Instruction pInstruction) {
		if (mFlag.equals("if")) {
			addInstructionIf(pInstruction);
		}
		else {
			addInstructionElse(pInstruction);
		}
		
	}



	@Override
	public void setFlag(String pVal) {
		mFlag = pVal;
		
	}
	

	@Override
	public String dump(String pDecal) {
		
		LOGGER.info(pDecal + mArgs.toString());
		mLstIf.dump(pDecal + "--");
		LOGGER.info(pDecal + "else : ");
		mLstElse.dump(pDecal + "--");
		LOGGER.info(pDecal + "endif");
		
		return "Cond:";
	}
	
	
	public String toString () {
		return mArgs.toString();
	}

}
