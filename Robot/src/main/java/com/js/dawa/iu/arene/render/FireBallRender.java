package com.js.dawa.iu.arene.render;

import java.awt.Graphics2D;

import com.js.dawa.model.robot.Position;

public class FireBallRender implements CaseRender {

	InfoRender mInfoRender;
	
	
	@Override
	public InfoRender getInfoRender() {
		
		return mInfoRender;
	}
	
	
	public void setInfoRender (InfoRender pInfoRender) {
		mInfoRender = pInfoRender;
		
	}


	@Override
	public void reinit() {
		//na reinit
		
	}


	@Override
	public void paint(Graphics2D pGraphics,int pX,int pY) {
		RenderString lRenderString = new RenderString();
		lRenderString.paint(pGraphics, new Position( pX,pY), mInfoRender);
		
	}

}
