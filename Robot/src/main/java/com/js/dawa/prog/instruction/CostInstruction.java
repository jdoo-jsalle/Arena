package com.js.dawa.prog.instruction;

import java.util.HashMap;
import java.util.Map;

public class CostInstruction {
	
	
	private Map<String, Integer> mLstCostInst = new HashMap<>();
	
	public void addCost(String pInstruction, int pCost) {
		mLstCostInst.put (pInstruction,Integer.valueOf(pCost));
	}
	
	public void affectCost (Instruction pInstruction) {
		Args lArgsInstruction = pInstruction.getArgs();
		Integer lVal = mLstCostInst.get(lArgsInstruction.getNameInstruction());
		//default value, if not exist
		int lCost = 10;
		if (lVal != null) {
			lCost = lVal.intValue();
		}
		lArgsInstruction.setCostInstruction(lCost);
	}
	


}
