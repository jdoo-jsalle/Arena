package com.js.dawa.iu.arene;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.RobotRender;
import com.js.dawa.robot.model.Position;

public class Robot implements ObjetArene{
	
	Position mPosition;
	
	
	RobotRender mRobotRender;

	

	
	public void setSymbol (String pSymbole) {
		mRobotRender = new RobotRender(pSymbole);
	}
	
	public CaseRender getRender () {
		return mRobotRender;
	}
	
	
	public void setPosition (Position pPosition) {
		mPosition = pPosition;
	}

	@Override
	public Position getPosition() {
			return mPosition;
	}

}
