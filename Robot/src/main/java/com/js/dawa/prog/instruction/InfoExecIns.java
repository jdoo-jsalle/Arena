package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * resultat system of an instruction
 */
public class InfoExecIns {
	
	private static final Logger LOGGER =  LogManager.getLogger( InfoExecIns.class );
	
	StringBuilder mMessage = new StringBuilder();
	String mComa="";
	
	
	
	private boolean isOver = true; //default value
	
	
	public InfoExecIns (Instruction pIntruction) {
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
	


}
