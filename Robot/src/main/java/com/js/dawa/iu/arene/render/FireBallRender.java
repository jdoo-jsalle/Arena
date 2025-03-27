package com.js.dawa.iu.arene.render;

public class FireBallRender implements CaseRender {

	InfoRender mInfoRender;
	
	
	@Override
	public InfoRender getInfoRender() {
		
		return mInfoRender;
	}
	
	
	public void setInfoRender (InfoRender pInfoRender) {
		mInfoRender = pInfoRender;
		
	}

}
