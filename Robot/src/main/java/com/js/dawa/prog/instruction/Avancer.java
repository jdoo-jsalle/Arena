package com.js.dawa.prog.instruction;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.Robot;
import com.js.dawa.robot.model.Position;
import com.js.dawa.util.DawaException;

public class Avancer implements Instruction {
	
	Args mArgs;
	int mX =0;
	int mY =0;
	Robot mRobot;
	Arene mArene;

	@Override
	public void init(Args pArgsInstruction, Robot pRobot,Arene pArene) throws DawaException {
		mArgs = pArgsInstruction;
		mRobot = pRobot;
		mArene = pArene;
		verify();

	}

	@Override
	public void execInstruction() {
		Position lPosition = mRobot.getPosition();
		int lSizeArene = mArene.getAreneProps().getSize();
		if (lPosition.getX()+ mX <= lSizeArene &&
			lPosition.getY()+ mY <= lSizeArene  &&
			lPosition.getX()+ mX > 0 &&
			lPosition.getY()+ mY > 0)
		{
			mRobot.setColorDeBlocked();
			mRobot.add(mX,mY);
		}
		else {
			mRobot.setColorBlocked();
		}
			
	
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
		
		return mArgs.toString();
	}

	
	

}
