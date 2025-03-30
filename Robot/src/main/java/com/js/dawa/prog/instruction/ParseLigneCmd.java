package com.js.dawa.prog.instruction;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.model.robot.Robot;
import com.js.dawa.util.DawaException;

public class ParseLigneCmd implements ParseLigne {
	 private static final Logger LOGGER =  LogManager.getLogger( ParseLigneCmd.class );
	
	 InstructionBlock mMainLstInstruction = new InstructionBlock();
	 
	 StackInstruction mPileInstruction = new StackInstruction();
	 
	 InstructionLst mCurrentInstruction;
	 
	 private Robot mRobot;

	 
	 public ParseLigneCmd () {
		 mPileInstruction.add(mMainLstInstruction);
		 mCurrentInstruction = mMainLstInstruction;
	 }
	
	 public void parse (int pNumLigne,String pLigne) throws DawaException {
		
		
		if (pLigne.trim().startsWith("if")) {
			Args lArgs = getArgs(pNumLigne, pLigne);
			
			InstructionLst lInstructionCond = new InstructionCond();
			lInstructionCond.init(lArgs, null, null);
			lInstructionCond.setFlag("if");
			mCurrentInstruction.addInstruction(lInstructionCond);
			mPileInstruction.add(lInstructionCond);
			LOGGER.info(">>>Pop {}",lInstructionCond);
			mCurrentInstruction = lInstructionCond;
			
		}
		else if (pLigne.trim().startsWith("else")){
			mCurrentInstruction.setFlag("else");
		
		}
		else if (pLigne.trim().startsWith("endif")) {
			//depile
			mCurrentInstruction = mPileInstruction.popAndPeek();
			LOGGER.info("<<<depop {}",mCurrentInstruction);
			
		
		}
		else {
			 Args lArgs = getArgs(pNumLigne, pLigne);
			 Instruction lIns= new InstructionFake();
			 lIns.init(lArgs, null, null);
			 mCurrentInstruction.addInstruction(lIns);
			 
			 LOGGER.info("<<<Add {} in {}",lIns,mCurrentInstruction);
			 
		}
		
	}
	 
	 
    public void setRobot (Robot pRobot) {
    	mRobot = pRobot;
    }
	 
	Args getArgs (int pNumLigne,String pLigne) throws DawaException {
		int lDeb = pLigne.indexOf("(");
		int lEnd = pLigne.indexOf(")");
		if (lDeb == -1 || lEnd == -1) {
			throw new DawaException("Ligne " + Integer.toString(pNumLigne) + " error must have param beetween ()");
		}
		String lInstruction = pLigne.substring(0,lDeb).trim();
		LOGGER.info("instruction : {}", lInstruction);
		Args lArgs = new Args(mRobot);
		lArgs.setNameInstruction(lInstruction);
		
		String lParam = pLigne.substring(lDeb + 1, lEnd).trim();
		if (!lParam.isEmpty()) {
			LOGGER.info("Param : \"{}\"", lParam);
			String [] lParams = lParam.split(",",0);
			
			for (String lVal : lParams) {
				LOGGER.info("val : {}", lVal);
				lArgs.addArguments(lVal);
			}
		}
		
		return lArgs;
		
	}

}
