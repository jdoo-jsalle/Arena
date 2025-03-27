package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

import javax.script.ScriptException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.robot.model.DataBoard;
import com.js.dawa.robot.model.Robot;
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
	
	private DataBoard mDataBoard;
	
	public Args (DataBoard pDataBoard) {
		mDataBoard = pDataBoard;
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
		if (lVar.startsWith(DEBVAR) && mDataBoard != null) {
			lVar = mDataBoard.getVariable(lVar.substring(1));
		}
		//manage Java Script eval
		if (lVar.startsWith(JS)) {
			String lClause = lVar.substring(JS.length());
			LOGGER.debug("Clause {}", lClause);
			ScriptJsEval lScriptEval = new ScriptJsEval(lClause);
			Robot lRobot = new Robot();
			lRobot.setRobotData(mDataBoard);
			try {
				lVar = lScriptEval.compute(lRobot);
			} catch (ScriptException e) {
				LOGGER.debug("error",  e);
			}
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
