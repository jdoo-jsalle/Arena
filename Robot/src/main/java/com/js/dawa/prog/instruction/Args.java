package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

import com.js.dawa.robot.model.DataBoard;
import com.js.dawa.util.DawaException;

public class Args {

	private String mNameInstruction;
	private List<String> mLstArgs  = new ArrayList<>();
	
	private DataBoard mDataBoard;
	
	public Args (DataBoard pDataBoard) {
		mDataBoard = pDataBoard;
	}
	
	public void addArguments (String pArg) {
		mLstArgs.add(pArg);
	}
	
	public List<String> getArguments (){
		return mLstArgs;
	}

	public String getNameInstruction() {
		return mNameInstruction;
	}

	public void setNameInstruction(String pNameInstruction) {
		this.mNameInstruction = pNameInstruction;
	}
	
	
	public int sizeArgs () {
		return mLstArgs.size();
	}
	
	
	public String getArgs (int pI) {
		String lVar = mLstArgs.get(pI);
		if (lVar.startsWith("$") && mDataBoard != null) {
			lVar = mDataBoard.getVariable(lVar.substring(1));
		}
		return  lVar;
	}
	
	public int getArgsInt (int pI) throws DawaException{
		String lVal = getArgs(pI);
		int lRes = -1;
		try {
			lRes = Integer.parseInt(lVal);
		}
		catch (NumberFormatException le) {
			throw new DawaException("Args " + Integer.toString(pI) + " must be numeric");
		}
		return lRes;
	}
	
	@Override
	public String toString() {
		return mNameInstruction + " : " + mLstArgs.toString();
	}
	
	
}
