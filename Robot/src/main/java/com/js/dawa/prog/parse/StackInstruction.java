package com.js.dawa.prog.parse;


import java.util.ArrayDeque;
import java.util.Deque;

import com.js.dawa.prog.instruction.InstructionLst;

public class StackInstruction {
	
	Deque<InstructionLst> mPileInstruction = new ArrayDeque<>();
	
	
	
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

	
	void clear () {
		mPileInstruction.clear();
	}
}
