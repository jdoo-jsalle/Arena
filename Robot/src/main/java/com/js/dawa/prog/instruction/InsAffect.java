package com.js.dawa.prog.instruction;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.robot.model.Robot;
import com.js.dawa.util.DawaException;

/**
 * Affect (NomVariable, Valeur)
 * positionne de DataBoard la valeur pour NomVariable
 * 
 */
public class InsAffect implements Instruction {
	
	Args mArgs;
	Robot mRobot;
	Arene mArene;

	@Override
	public void init(Args pArgsInstruction, Robot pRobot, Arene pArene) throws DawaException {
		mArgs = pArgsInstruction;
		mRobot = pRobot;
		mArene = pArene;

	}

	@Override
	public void execInstruction() throws DawaException {
		String lKey = mArgs.getArgs(0);
		String lVal = mArgs.getArgs(1);
		//affect au DataBoard du robot
		mRobot.getRobotData().setVariable(lKey, lVal);

	}

	@Override
	public void setFlag(String pVal) {
		// TODO Auto-generated method stub

	}

	@Override
	public String dump(String pDecal) {
		// TODO Auto-generated method stub
		return null;
	}

}
