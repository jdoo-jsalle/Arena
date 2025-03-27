package com.js.dawa.iu.arene.render;

import com.js.dawa.robot.model.RobotsProps;

public class RobotRender implements CaseRender {
	
	
	InfoRender mInfoRender = new InfoRender();
	
	public RobotRender (RobotsProps pRobotProps) {
		mInfoRender.setString(pRobotProps.getNom());
		mInfoRender.setColor(pRobotProps.getColor());
		
	}

	@Override
	public InfoRender getInfoRender() {
		
		return  mInfoRender;
	}
	
	public void setColor (String pColor) {
		mInfoRender.setColor(pColor);
	}

}
