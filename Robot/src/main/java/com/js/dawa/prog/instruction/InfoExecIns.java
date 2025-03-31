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
	
	List<InfoExecIns> mLstInfoExecIns = new ArrayList<>();
	
	Instruction mIntructionRun;
	
	private boolean isOver = true; //default value
	
	
	public InfoExecIns (Instruction pIntruction) {
		mIntructionRun = pIntruction;
	}

	public boolean isOver() {
		return isOver;
	}

	public void setOver(boolean pIsOver) {
		this.isOver = pIsOver;
	}
	
	void addInfoExecIns (InfoExecIns pInfoExecIns) {
		if (LOGGER.isDebugEnabled()) {
		    LOGGER.debug (")))> add : {}",pInfoExecIns.dump());
		}
		mLstInfoExecIns.add(pInfoExecIns);
	}
	
	public String dump() {
		StringBuilder  lExec = new StringBuilder(mIntructionRun.toString());
		String lComa =",";
		if (LOGGER.isDebugEnabled())
		    LOGGER.debug("****> {}",this);
		for (InfoExecIns lInfoExec : mLstInfoExecIns) {
			lExec.append(lComa);
			lExec.append(lInfoExec.dump());
			
	
		}
		
		return lExec.toString();
		
	}
	
	@Override
	public String toString() {
		StringBuilder lRes = new StringBuilder();
		lRes.append(mIntructionRun.toString());
		lRes.append("===>");
		lRes.append(mLstInfoExecIns.toString());
		return lRes.toString();
	}
	


}
