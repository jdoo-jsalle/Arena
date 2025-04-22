package com.js.dawa.prog.instruction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * resultat system of an instruction
 */
public class InfoExecIns {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( InfoExecIns.class );
	
	StringBuilder mMessage = new StringBuilder();
	String mComa="";
	
	InfoExecIns mLastInfoExec;
	
	Instruction mInstruction;
	
	
	private boolean isOver = true; //default value
	
	
	public InfoExecIns (Instruction pIntruction) {
		mInstruction = pIntruction;
		mMessage.append(pIntruction.toString());
		mMessage.append(":");
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean pIsOver) {
		this.isOver = pIsOver;
	}
	
	void addInfoExecIns (InfoExecIns pInfoExecIns) {
		mLastInfoExec = pInfoExecIns;
		if (LOGGER.isDebugEnabled()) {
		    LOGGER.debug (")))> add : {}",pInfoExecIns);
		}
		mMessage.append(mComa);
		mMessage.append(pInfoExecIns.toString());
		mComa =",";
	}
	
	
	@Override
	public String toString() {
		return mMessage.toString();
	}
	
	public InfoExecIns getLastInfoExec () {
		if (mLastInfoExec != null) {
			return mLastInfoExec.getLastInfoExec();
		}
		else {
			return this;
		}
	}
	
	public Instruction getInstruction () {
		return mInstruction;
	}
	


}
