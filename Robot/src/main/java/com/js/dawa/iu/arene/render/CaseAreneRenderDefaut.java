package com.js.dawa.iu.arene.render;

public class CaseAreneRenderDefaut implements CaseRender {
	
	InfoRender mInfoRender = new InfoRender("x");

	@Override
	public InfoRender getInfoRender() {
		return mInfoRender;
	}

}
