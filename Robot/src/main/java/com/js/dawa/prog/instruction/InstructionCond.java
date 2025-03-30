package com.js.dawa.prog.instruction;

import javax.script.ScriptException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

public class InstructionCond implements InstructionLst {
	
	private static final Logger LOGGER =  LogManager.getLogger( InstructionCond.class );
	
	InstructionBlock mLstIf = new InstructionBlock();
	InstructionBlock mLstElse = new InstructionBlock();
	
	String mFlag;
	
	Args mArgs;
	
	ObjetArene mRobot;

	@Override
	public void init(Args pArgsInstruction, ObjetArene pRobot, Arene pArene) {
		mArgs = pArgsInstruction;
		mRobot = pRobot;

	}
	
	
	
	public void addInstructionIf (Instruction pInstruction) {
		mLstIf.addInstruction(pInstruction);
	}
	
	public void addInstructionElse(Instruction pInstruction) {
		mLstElse.addInstruction(pInstruction);
	}

	@Override
	public void execInstruction() throws DawaException {

		if (execCondition()) {
			mLstIf.execInstruction();
		}
		else {
			mLstElse.execInstruction();
		}
	

	}
	
	
	boolean execCondition()  {
		boolean lRes = false;
		
		try {
			ScriptJsEval lIfEval = new ScriptJsEval(mArgs.getArgs(0));
			lRes =lIfEval.eval(mRobot);
		}
		catch (ScriptException le) {
			LOGGER.debug("error script ",le);
		}
		
		
		return lRes;
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
