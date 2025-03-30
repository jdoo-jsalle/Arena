package com.js.dawa.prog.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.Test;

import com.js.dawa.prog.instruction.Args;
import com.js.dawa.prog.instruction.InstructionBlock;
import com.js.dawa.prog.parse.StackInstruction;

public class TestStackInstruction {
	
	@Test
	public void pop () {
		
		StackInstruction lStackInstruction = new StackInstruction();
		
		Args lArgs = new Args(null);
		lArgs.setNameInstruction("top");
		InstructionBlock lInstructionBlock = new InstructionBlock();
		lInstructionBlock.init(lArgs, null, null);
		
		lStackInstruction.add(lInstructionBlock);
		
		Args lArgs2 = new Args(null);
		lArgs2.setNameInstruction("middle");
		InstructionBlock lInstructionBlock2 = new InstructionBlock();
		lInstructionBlock2.init(lArgs2, null, null);
		
		lStackInstruction.add(lInstructionBlock2);
		
		Args lArgs3 = new Args(null);
		lArgs3.setNameInstruction("last");
		InstructionBlock lInstructionBlock3 = new InstructionBlock();
		lInstructionBlock3.init(lArgs3, null, null);
		
		lStackInstruction.add(lInstructionBlock3);
		
		
		InstructionBlock l = (InstructionBlock)lStackInstruction.pop();
		assertEquals ("last",l.getArgs().getNameInstruction());
		
		l = (InstructionBlock)lStackInstruction.pop();
		assertEquals ("middle",l.getArgs().getNameInstruction());
		
		l = (InstructionBlock)lStackInstruction.pop();
		assertEquals ("top",l.getArgs().getNameInstruction());
		
		l = (InstructionBlock)lStackInstruction.pop();
		assertNull(l);
	}

}
