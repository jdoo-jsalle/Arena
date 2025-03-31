package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

/**
 * resultat system of an instruction
 */
public class InfoExecIns {
	
	
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
		mLstInfoExecIns.add(pInfoExecIns);
	}
	
	public String dump() {
		StringBuilder  lExec = new StringBuilder(mIntructionRun.toString());
		String lComa ="";
		for (InfoExecIns lInfoExec : mLstInfoExecIns) {
			lExec.append(lComa);
			lExec.append(lInfoExec.dump());
			
			lComa = ",";
		}
		
		return lExec.toString();
		
	}
	


}
