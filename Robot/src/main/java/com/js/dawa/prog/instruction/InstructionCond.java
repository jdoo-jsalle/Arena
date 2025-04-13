package com.js.dawa.prog.instruction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

/**
 * Define condition 
 * with one argument wiht java script syntax
 */
public class InstructionCond implements InstructionLst {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( InstructionCond.class );
	
	InstructionBlock mLstIf = new InstructionBlock();
	InstructionBlock mLstElse = new InstructionBlock();
	
	String mFlag;
	
	Args mArgs;
	
	ObjetArene mRobot;
	
	boolean mForceIf = false;
	boolean mForceElse = false;
	
	InfoExecIns mInfoExec;

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
	public InfoExecIns execInstruction() throws DawaException {
			
		if (mForceIf) {
			LOGGER.debug("force if");
			 execIf();
		}
		else if (mForceElse){
			LOGGER.debug("force else");
			execElse();
		}
		else {
			mInfoExec =new InfoExecIns(this);
			mInfoExec.setOver(false);
			if (mArgs != null) {//cost of if
				mArgs.deductCostToObjectArena();
			}
			LOGGER.debug("Eval condition");
			if (execCondition()) {
				
				LOGGER.debug("Exec if on condition");
				execIf();
			}
			else {
				LOGGER.debug("Exec else on condition");
				execElse();
			}
		}
		return mInfoExec;
	

	}
	
	void execIf () throws DawaException {
		InfoExecIns lInfoExec = mLstIf.execInstruction();
		mForceIf = !lInfoExec.isOver();
		mInfoExec.addInfoExecIns(lInfoExec);
		mInfoExec.setOver(!mForceIf);
	}
	
	void execElse () throws DawaException {
		InfoExecIns lInfoExec = mLstElse.execInstruction();
		mForceElse = !lInfoExec.isOver();
		mInfoExec.addInfoExecIns(lInfoExec);
		mInfoExec.setOver(!mForceElse);
		
	}
	
	
	boolean execCondition()  {
		ScriptJsEval lIfEval = new ScriptJsEval(mArgs.getArgs(0));
		return lIfEval.eval(mRobot);
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
		StringBuilder lRes = new StringBuilder ();
		lRes.append(toString());
		lRes.append("\n");
		lRes.append(mLstIf.dump(pDecal + "--") );
		lRes.append("else\n");
		lRes.append(mLstElse.dump(pDecal + "--"));
		lRes.append("endif");

		
		return lRes.toString();
	}
	
	
	public String toString () {
		return mArgs.toString();
	}



	@Override
	public Args getArgs() {
		return mArgs;
	}
	
	@Override
	public boolean isOver() {
		return mInfoExec!= null && mInfoExec.isOver();
	}

}
