package com.js.dawa.iu.arene.render;

import java.awt.Graphics2D;

import com.js.dawa.model.position.Position;

public interface CaseRender {
	
	InfoRender getInfoRender();
	void setInfoRender (InfoRender pInfoRender);
	void reinit();
	void paint (Graphics2D pGraphics,Position pPosition);
	
	default boolean isObsolete () {
		return false;
	} 
	
}
