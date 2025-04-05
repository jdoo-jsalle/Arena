package com.js.dawa.prog.instruction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.util.DawaException;

/**
 * Avancer (x,y)
 *    x et y : entier positif ou negatif ou variable ($<var> qui 
 *    doit être dans son DataBoard, ou un JS: pour évaluer un javascript
 */
public class InsAvancer implements Instruction {
	
	private static final Logger LOGGER =  LogManager.getLogger( InsAvancer.class );
	
	Args mArgs;
	int mX =0;
	int mY =0;
	ObjetArene mRobot;
	Arene mArene;

	@Override
	public void init(Args pArgsInstruction, ObjetArene pRobot,Arene pArene) throws DawaException {
		mArgs = pArgsInstruction;
		mRobot = pRobot;
		mArene = pArene;
		verify();

	} 

	@Override
	public InfoExecIns execInstruction() throws DawaException {
		verify();
				
		if (mArene != null && mArene.isNewPositionIsOk(mRobot, mX, mY)){
			mRobot.setInArena(true);
			mRobot.add(mX,mY);
		}
		else {
			mRobot.setInArena(false);
		}
		
		return new InfoExecIns(this);
		
	
	}
	
	
	void verify () throws DawaException {
		//1:int (x) 2:int (y)
		mX = mArgs.getArgsInt(0);
		mY = mArgs.getArgsInt(1);
		
	}

	@Override
	public void setFlag(String pVal) {
		//na
		
	}

	@Override
	public String dump(String pDecal) {
		
		return pDecal + toString();
	}
	
	@Override
	public String toString () {
		return  "Avancer : " + mArgs.toString();
	}

	@Override
	public Args getArgs() {
		return mArgs;
	}

	
	

}
