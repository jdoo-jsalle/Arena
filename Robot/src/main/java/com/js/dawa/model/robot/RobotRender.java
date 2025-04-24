package com.js.dawa.model.robot;

import java.awt.Graphics2D;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.iu.arene.render.RenderString;
import com.js.dawa.model.position.Position;

public class RobotRender implements CaseRender {
	
	
	RobotsProps mRobotProps;
	
	InfoRender mInfoRender = new InfoRender();
	
	public RobotRender (RobotsProps pRobotProps) {
		mRobotProps = pRobotProps;
			
	}
	

	@Override
	public InfoRender getInfoRender() {
		return  mInfoRender;
	}
	
	
	@Override
	public void setInfoRender(InfoRender pInfoRender) {
		mInfoRender = pInfoRender;
	}

	@Override
	public void reinit() {
		mInfoRender.setString(mRobotProps.getNom());
		mInfoRender.setColor(mRobotProps.getColor());
		
	}
	
	public void setBlocked() {
		mInfoRender.setColor("DARK_GRAY");
		
	}
	
	public void setHide () {
		mInfoRender.setColor("white");
	}
	
	public void setHurt () {
		mInfoRender.setString("!*!");
		mInfoRender.setColor("red"); 
	} 


	@Override
	public void paint(Graphics2D pGraphics,int pX,int pY) {
		RenderString lRenderString = new RenderString();
		lRenderString.paint(pGraphics, new Position( pX,pY), mInfoRender);
		
	}

}
