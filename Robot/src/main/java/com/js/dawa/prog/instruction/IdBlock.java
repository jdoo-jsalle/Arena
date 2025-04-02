package com.js.dawa.prog.instruction;

public class IdBlock {
	
	private static int mIdBlockTot = 0;
	
	static void addIdBlockTot() {
		mIdBlockTot ++;
	}
	
	public static void reinitIdBlock() {
		mIdBlockTot = 0;
	}
	
	public static int getIdBlock () {
		int lRes = mIdBlockTot;
		addIdBlockTot();
		return lRes;
	}

}
