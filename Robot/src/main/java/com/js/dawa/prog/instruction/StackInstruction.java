package com.js.dawa.prog.instruction;


import java.util.Stack;

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
