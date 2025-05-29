package com.js.dawa.prog;

import com.js.dawa.prog.instruction.CostInstruction;
import com.js.dawa.util.DawaException;
import com.js.dawa.util.In;

public class ParserCostInstruction {
	
	
	CostInstruction mCostInstruction = new CostInstruction();
	
	String mPath;
	
	ParserCostInstruction(String pPath){
		mPath = pPath;
	}
	
	void initCostInstruction () {
		mCostInstruction.addCost("affect", 0);
		mCostInstruction.addCost("block", 0);
		mCostInstruction.addCost("if", 1);
		mCostInstruction.addCost("avancer", 4);
		mCostInstruction.addCost("fake", 2);
		mCostInstruction.addCost("tir", 3);
		mCostInstruction.addCost("invisible", 20);
		mCostInstruction.addCost("mine", 10);
		mCostInstruction.addCost("fuite", 4);
		mCostInstruction.addCost("poursuite", 4);
		//
		mCostInstruction.addCost("rotate", 1);
		
		
	}
	
	
	void loadCost (String pPath) throws DawaException {
		initCostInstruction();
		if (pPath != null) {
			try (In lIn = new In()){
				lIn.open(mPath + pPath);
				String lLigne = lIn.readLine();
				while (lLigne != null) {
					String[] lVals = lLigne.split("=");
					String lKey = lVals[0];
					int lCost = Integer.parseInt(lVals[1].trim());	
					mCostInstruction.addCost(lKey, lCost);
					lLigne = lIn.readLine();
				}
				
			}
		}
	}

}
