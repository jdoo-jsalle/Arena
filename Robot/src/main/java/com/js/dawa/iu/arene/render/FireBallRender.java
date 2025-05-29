package com.js.dawa.iu.arene.render;

import java.awt.Graphics2D;

import com.js.dawa.model.position.Position;

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
	public void paint(Graphics2D pGraphics,Position pPosition) {
		RenderString lRenderString = new RenderString();
		lRenderString.paint(pGraphics, pPosition, mInfoRender);
		
	}

}
