package com.js.dawa.model.robot;

import java.awt.Graphics2D;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.iu.arene.render.RenderString;
import com.js.dawa.model.position.Position;

public class RobotRender implements CaseRender {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( RobotRender.class );
	
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
		mInfoRender.setColor("gray");
	}
	
	public void setHurt () {
		mInfoRender.setString("!*!");
		mInfoRender.setColor("red"); 
	} 


	@Override
	public void paint(Graphics2D pGraphics,Position pPosition) {
		RenderString lRenderString = new RenderString();
		int lX= pPosition.getXi();
		int lY= pPosition.getYi();
		lRenderString.paint(pGraphics, pPosition, mInfoRender);
		pGraphics.drawOval(lX+5, lY+5, 10, 10);//circle
		Position lVector = pPosition.getAxe().getVector();
		
		LOGGER.debug("paint with vector {} {}",lVector,pPosition.getAxe().getDir());
		
		int lX2 = lX- (int) (lVector.getX() * 10);
		int lY2 = lY - (int) (lVector.getY() * 10);
		
		
		
		pGraphics.drawLine(lX+10, lY+10, lX2+10 , lY2+10);
		
	}
	
	public void addDecorateur (String pVal) {
		mInfoRender.addDecorateur(pVal);
	}

}
