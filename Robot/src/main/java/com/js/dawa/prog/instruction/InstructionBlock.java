package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.robot.model.Robot;
import com.js.dawa.util.DawaException;

public class InstructionBlock implements InstructionLst {
	
	private static final Logger LOGGER =  LogManager.getLogger( InstructionBlock.class );

	Args mArgs;
	
	List<Instruction> mLstInstruction = new ArrayList<>();
	

	@Override
	public void init(Args pArgsInstruction, Robot pRobot, Arene pArene) {
		mArgs = pArgsInstruction;
		
	}


	@Override
	public void execInstruction() throws DawaException{
		for (Instruction lInstruction : mLstInstruction) {
			lInstruction.execInstruction();
		}
		
	}


	@Override
	public void addInstruction(Instruction pInstruction) {
		mLstInstruction.add(pInstruction);
		
	}


	@Override
	public void setFlag(String pVal) {
		//na
		
	}


	@Override
	public String dump(String pDecal) {
		for (Instruction lInstruction : mLstInstruction) {
			LOGGER.info("{} {}",pDecal, lInstruction.dump(pDecal));
		}
		
		return mArgs.toString();
	}
	
	
	
	
	
}
