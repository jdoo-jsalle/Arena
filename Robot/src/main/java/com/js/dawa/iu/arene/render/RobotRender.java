package com.js.dawa.iu.arene.render;

public class RobotRender implements CaseRender {
	
	String mSymbole ="?";
	
	public RobotRender (String pSymbole) {
		if (pSymbole != null)
		    mSymbole = pSymbole;
	}

	@Override
	public String getStringRender() {
		
		return mSymbole;
	}

}
