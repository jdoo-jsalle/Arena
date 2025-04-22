package com.js.dawa.iu.arene.render;

public class CaseAreneRenderEmpty implements CaseRender {
	
	InfoRender mInfoRender = new InfoRender(".");

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

}
