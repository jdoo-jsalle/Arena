package com.js.dawa.iu.arene.render;

public class HurtObjetRender implements CaseRender{
	
	InfoRender mInfoRender = new InfoRender();
	
	
	public HurtObjetRender () {
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
	public boolean isSecondary () {
		return true;
	}

}
