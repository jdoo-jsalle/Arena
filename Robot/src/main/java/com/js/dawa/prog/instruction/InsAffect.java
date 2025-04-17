package com.js.dawa.prog.instruction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

/**
 * Affect (NomVariable, Valeur)
 * positionne de DataBoard la valeur pour NomVariable
 * 
 */
public class InsAffect implements Instruction {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( InsAffect.class );
	
	Args mArgs;
	ObjetArene mRobot;
	Arene mArene;

	@Override
	public void init(Args pArgsInstruction, ObjetArene pRobot, Arene pArene) throws DawaException {
		mArgs = pArgsInstruction;
		mRobot = pRobot;
		mArene = pArene;

	}

	@Override
	public InfoExecIns execInstruction() throws DawaException {
		String lKey = mArgs.getArgs(0);
		String lVal = mArgs.getArgsWithSub(1);
		//affect au DataBoard du robot
		mRobot.getDataBoard().setVariable(lKey, lVal);
		LOGGER.debug("affect in Board {} : {}", lKey,lVal);
		return new InfoExecIns(this);

	}


	@Override
	public String dump(String pDecal) {
		return pDecal + toString();
	}
	
	@Override
	public String toString () {
		return "Affect :  " + mArgs.toString();
	}

	@Override
	public Args getArgs() {
		return mArgs;
	}



}
