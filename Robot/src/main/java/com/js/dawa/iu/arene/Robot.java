package com.js.dawa.iu.arene;

import java.awt.Color;
import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.RobotRender;
import com.js.dawa.robot.model.Attribut;
import com.js.dawa.robot.model.Position;

public class Robot implements ObjetArene{
	
	Position mPosition;
	
	RobotsProps mRobotProps;
	
	RobotRender mRobotRender;

	

	
	public void init (RobotsProps pRobotProps) {
		mRobotProps = pRobotProps;
		mRobotRender = new RobotRender(pRobotProps);
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

	@Override
	public Map<String, Attribut> getProps() {
		return mRobotProps.getLstAttribut();
	}
	
	public void setColorBlocked() {
		mRobotRender.setColor("DARK_GRAY");
		
	}
	
	public void setColorDeBlocked() {
		mRobotRender.setColor(mRobotProps.getColor());
	}

	
	public void add (int pX, int pY) {
		mPosition.addX(pX);
		mPosition.addY(pY);
	}
	
	
	
}
