package com.js.dawa.iu.arene.render;

import java.awt.Graphics2D;

import com.js.dawa.model.robot.Position;

public class HurtObjetRender implements CaseRender{
	
	InfoRender mInfoRender = new InfoRender();
	long mTimeStart;
	
	public HurtObjetRender () {
		mTimeStart = System.currentTimeMillis();
		reinit();
	}

	@Override
	public InfoRender getInfoRender() {
		
		return mInfoRender;
	}

	@Override
	public void setInfoRender(InfoRender pInfoRender) {
		mInfoRender = pInfoRender;
		
	}
	
	public void setString (String pVal) {
		mInfoRender.setString(pVal);
	}

	@Override
	public void reinit() {
		mInfoRender.setString("!*");	
		mInfoRender.setColor("red");
		
	}
	
	@Override
	public boolean isObsolete () {
		return System.currentTimeMillis() - mTimeStart > 1000;//obsolete in 1 second
	}

	@Override
	public void paint(Graphics2D pGraphics,int pX,int pY) {
		RenderString lRenderString = new RenderString();
		lRenderString.paint(pGraphics, new Position( pX,pY), mInfoRender);
		
	}

}
