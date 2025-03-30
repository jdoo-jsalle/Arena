package com.js.dawa.prog.parse;


import java.util.Stack;

import com.js.dawa.prog.instruction.InstructionLst;

public class StackInstruction {
	
	Stack<InstructionLst> mPileInstruction = new Stack<>();
	
	
	
	void add(InstructionLst pInstructionLst) {
		 mPileInstruction.push(pInstructionLst);
	}
	
	
	InstructionLst pop () {
		if (!mPileInstruction.isEmpty())
			return mPileInstruction.pop();
		else 
			return null;
	}
	
	InstructionLst peek() {
		if (!mPileInstruction.isEmpty())
			return mPileInstruction.peek();
		else 
			return null;
		
	}
	
	InstructionLst popAndPeek () {
		pop();
		return peek();
	}

}
