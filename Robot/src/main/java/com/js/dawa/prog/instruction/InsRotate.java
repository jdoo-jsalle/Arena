package com.js.dawa.prog.instruction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

public class InsRotate implements Instruction {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( InsRotate.class );
	ObjetArene mRobot;
	Arene mArene;
	Args mArgs;
	DeplaOnOtherObject mDepla;
	
	int mRot;
	
	
	
	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArena, Arene pArene) throws DawaException {
		mArene = pArene;
		mArgs = pArgsInstruction;
		mRobot = pObjetArena;
		mDepla = new DeplaOnOtherObject(mRobot, mArene);
		verify();
		
	}

	@Override
	public InfoExecIns execInstruction() throws DawaException {
		LOGGER.debug("rotation for {}",mRot);
		mRobot.getPosition().getAxe().doRotation(mRot);
		LOGGER.debug("new rotation is {}",mRobot.getPosition());
		
		return new InfoExecIns(this);
	}

	@Override
	public String dump(String pDecal) {
		return toString();
	}

	@Override
	public Args getArgs() {
		return mArgs;
	}
	
	
	void verify () throws DawaException {
		//1:rotate (x)
		mRot = mArgs.getArgsInt(0);
		
		
	}
	
	@Override
	public String toString () {
		return "Rotate :  " + mArgs.toString();
	}

}
