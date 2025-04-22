package com.js.dawa.iu.arene.render;



public interface CaseRender {
	
	InfoRender getInfoRender();
	void setInfoRender (InfoRender pInfoRender);
	void reinit();
	
	default boolean isSecondary () {
		return false;
	}
	
}
