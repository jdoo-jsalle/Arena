package com.js.dawa.prog.parse;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.prog.instruction.Args;
import com.js.dawa.prog.instruction.CostInstruction;
import com.js.dawa.prog.instruction.InsFake;
import com.js.dawa.prog.instruction.Instruction;
import com.js.dawa.prog.instruction.InstructionBlock;
import com.js.dawa.prog.instruction.InstructionCond;
import com.js.dawa.prog.instruction.InstructionLst;
import com.js.dawa.util.DawaException;

public class ParseLigneCmd implements ParseLigne {
	 private static final Logger LOGGER =  LogManager.getLogger( ParseLigneCmd.class );
	
	 InstructionBlock mMainLstInstruction = new InstructionBlock();//prg loop
	 InstructionBlock mInitLstInstruction = new InstructionBlock(-1);//prg init
	 
	 StackInstruction mPileInstruction = new StackInstruction();
	 
	 InstructionLst mCurrentInstruction;
	 
	 FabricInstructionFromString mFabricInstruction = new FabricInstructionFromString();
	 
	 private ObjetArene mObjetArene;
	 private Arene mArene;
	 
	 int mNumLigne;
	 
	 
	 private CostInstruction mCostInstruction;
	 

	 
	 public ParseLigneCmd (ObjetArene pObjetArene, Arene pArene) {
		 mObjetArene = pObjetArene;
		 mArene = pArene;
		 mPileInstruction.add(mMainLstInstruction);
		 mCurrentInstruction = mMainLstInstruction;
	 }
	
	 public void parse (String pLigne) throws DawaException {
		if (pLigne.equals ("init")){
			mPileInstruction.add(mInitLstInstruction);
			mCurrentInstruction = mInitLstInstruction;
		}
		else if (pLigne.equals("endinit")) {
			 mPileInstruction.clear();
			 mPileInstruction.add(mMainLstInstruction);
			 mCurrentInstruction = mMainLstInstruction;
		}
		
		else if (pLigne.trim().startsWith("if")) {
			Args lArgs = getArgs(mNumLigne, pLigne);
			
			
			InstructionLst lInstructionCond = new InstructionCond();
		
			lInstructionCond.init(lArgs, mObjetArene, mArene);
			affectCost(lInstructionCond);
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
			 Args lArgs = getArgs(mNumLigne, pLigne);
			 Instruction lIns=null;
			 try {
				 //create the instance instruction
				 lIns= mFabricInstruction.createInstance(lArgs.getNameInstruction());
			 }
			 catch (DawaException le) {
				 LOGGER.debug("Error", le);
				 lIns = new InsFake();
				 //TODO : throw manager error
			 }
			
			 lIns.init(lArgs, mObjetArene, mArene);
			 affectCost(lIns);
			 mCurrentInstruction.addInstruction(lIns);
			 
			 LOGGER.info("<<<Add {} in {}",lIns,mCurrentInstruction);
			 
		}
		
		mNumLigne++;
		
	}
	 

	 
	Args getArgs (int pNumLigne,String pLigne) throws DawaException {
		int lDeb = pLigne.indexOf("(");
		int lEnd = pLigne.indexOf(")");
		if (lDeb == -1 || lEnd == -1) {
			throw new DawaException("Ligne #" + Integer.toString(pNumLigne) + " \"" + pLigne + "\" : error must have param beetween ()");
		}
		String lInstruction = pLigne.substring(0,lDeb).trim();
		LOGGER.info("instruction : {}", lInstruction);
		Args lArgs = new Args(mObjetArene);
		lArgs.setNameInstruction(lInstruction);
		
		String lParam = pLigne.substring(lDeb + 1, lEnd).trim();
		if (!lParam.isEmpty()) {
			LOGGER.info("Param : \"{}\"", lParam);
			String [] lParams = lParam.split(",",0);
			
			for (String lVal : lParams) {
				LOGGER.info("val : {}", lVal);
				lArgs.addArguments(lVal.trim());
			}
		}
		
		return lArgs;
		
	}
	
	public Instruction getMain() {
		return mMainLstInstruction;
	}
	
	public Instruction getInit () {
		return mInitLstInstruction;
	}

	public CostInstruction getCostInstruction() {
		return mCostInstruction;
	}

	public void setCostInstruction(CostInstruction pCostInstruction) {
		this.mCostInstruction = pCostInstruction;
	}
	
	void affectCost (Instruction pInstruction) {
		if (mCostInstruction != null)
		   mCostInstruction.affectCost(pInstruction);
	}
	
	

}
