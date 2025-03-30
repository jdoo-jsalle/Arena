package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

/**
 * Arg 
 *   value
 *   $Value => seek value in DataBoard
 *   JS:Clause => eval javascript clause and resolve it
 */
public class Args {
	
	private static final Logger LOGGER =  LogManager.getLogger( Args.class );

	private String mNameInstruction;
	private List<String> mLstArgs  = new ArrayList<>();
	
	static String JS ="JS:";
	
	static String DEBVAR ="$";
	
	static String RANDOM ="Rand";
	
	private ObjetArene mRobot;
	
	public Args (ObjetArene pRobot) {
		mRobot = pRobot;
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
		String lVar = mLstArgs.get(pI);
		//manage variable
		CmdOnValue lCmdOnValue = null;
		if (lVar.startsWith(DEBVAR) && mRobot.getDataBoard() != null) {
			lCmdOnValue = new CmdOnValueVariable();
			
		}
		//manage Java Script eval
		else if (lVar.startsWith(JS)) {
			lCmdOnValue = new CmdOnValueJavaScript();
		}
		//manage random generation
		else if (lVar.startsWith(RANDOM)) {
			lCmdOnValue = new CmdOnValueRandom();
		}
		if (lCmdOnValue != null) {
			lCmdOnValue.init(mRobot);
			lVar = lCmdOnValue.computeVal(lVar);
		}
		
		
		return  lVar;
	}
	
	public int getArgsInt (int pI) throws DawaException{
		String lVal = getArgs(pI);
		int lRes = -1; 
		try {
			lRes = Integer.parseInt(lVal);
		}
		catch (NumberFormatException le) {
			throw new DawaException("Args " + Integer.toString(pI) + " must be numeric");
		}
		return lRes;
	}
	
	@Override
	public String toString() {
		return mNameInstruction + " : " + mLstArgs.toString();
	}
	
	
}
