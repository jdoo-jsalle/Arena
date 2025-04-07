package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

/**
 * Arg 
 *   value
 *   $Value => seek value in DataBoard
 *   JS:Clause => eval javascript clause and resolve it
 */
public class Args {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( Args.class );

	private String mNameInstruction;
	
	private List<String> mLstArgs  = new ArrayList<>();
	
	private ObjetArene mObjetArene;
	
	private ArgString mArgsString;
	
	private int mCostInstruction= 0;
	
	public Args (ObjetArene pObjetArene) {
		mObjetArene = pObjetArene;
	}
	
	public void addArguments (String pArg) {
		mLstArgs.add(pArg);
	}
	
	public List<String> getArguments (){
		return mLstArgs;
	}

	public String getNameInstruction() {
		return mNameInstruction;
	}

	public void setNameInstruction(String pNameInstruction) {
		this.mNameInstruction = pNameInstruction;
	}
	
	
	public int sizeArgs () {
		return mLstArgs.size();
	}
	
	
	public String getArgs (int pI) {
		if (mArgsString == null) {
			mArgsString = new ArgString(mObjetArene,mLstArgs);
		}
		return  mArgsString.getArgs(pI);
	}
	
	public int getArgsInt (int pI) throws DawaException{
		String lVal = getArgs(pI);
		int lRes = 0; 
		if (lVal != null) {
			
			try {
				lRes = Integer.parseInt(lVal);
			}
			catch (NumberFormatException le) {
				throw new DawaException("Value \"" + lVal + "\" index :  " + Integer.toString(pI) +" must be numeric");
			}
		}
		return lRes;
	}
	
	@Override
	public String toString() {
		return mNameInstruction + " : " + mLstArgs.toString();
	}
	
	public void setCostInstruction (int pCostInstruction) {
		mCostInstruction = pCostInstruction;
	}
	
	public void deductCostToObjectArena() {
		mObjetArene.getEnergie().add(-mCostInstruction);
	}
	
	
}
