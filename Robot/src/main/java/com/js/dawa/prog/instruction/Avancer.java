package com.js.dawa.prog.instruction;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.Robot;
import com.js.dawa.robot.model.Position;

public class Avancer implements Instruction {
	
	Args mArgs;
	int mX =1;
	int mY =1;
	Robot mRobot;
	Arene mArene;

	@Override
	public void init(Args pArgsInstruction, Robot pRobot,Arene pArene) {
		mArgs = pArgsInstruction;
		mRobot = pRobot;
		mArene = pArene;
		verify();

	}

	@Override
	public Position exec(Args pArgs) {
		Position lPosition = mRobot.getPosition();
		int lSizeArene = mArene.getAreneProps().getSize();
		if (lPosition.getX()+ mX <= lSizeArene &&
			lPosition.getY() + mY <= lSizeArene) {
			mRobot.setColorDeBlocked();
			mRobot.add(mX,mY);
		}
		else {
			mRobot.setColorBlocked();
		}
			
		return null;
	}
	
	
	void verify () {
		//1:int (x) 2:int (y)
	}
	

}
