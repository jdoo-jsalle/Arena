package com.js.dawa.iu.arene.render;

import java.awt.Graphics2D;

public interface CaseRender {
	
	InfoRender getInfoRender();
	void setInfoRender (InfoRender pInfoRender);
	void reinit();
	void paint (Graphics2D pGraphics, int pX, int pY);
	
	default boolean isSecondary () {
		return false;
	} 
	
}
