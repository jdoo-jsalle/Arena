package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

public class Args {

	private String mNameInstruction;
	private List<String> mLstArgs  = new ArrayList<>();
	
	
	
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
		return  mLstArgs.get(pI);
	}
	
	
}
