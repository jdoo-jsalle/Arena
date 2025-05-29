package com.js.dawa.iu.arene.render;

import java.awt.Graphics2D;

import com.js.dawa.model.position.Position;

public class CaseAreneRenderDefaut implements CaseRender {
	
	InfoRender mInfoRender = new InfoRender("x");

	@Override
	public InfoRender getInfoRender() {
		return mInfoRender;
	}

	@Override
	public void setInfoRender(InfoRender pInfoRender) {
		mInfoRender = pInfoRender;
		
	}

	@Override
	public void reinit() {
		//na
		
	}

	@Override
	public void paint(Graphics2D pGraphics,Position pPosition) {
		RenderString lRenderString = new RenderString();
		lRenderString.paint(pGraphics, pPosition, mInfoRender);
		
	}

}
