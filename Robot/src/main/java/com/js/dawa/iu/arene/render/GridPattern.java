package com.js.dawa.iu.arene.render;

import java.awt.Graphics2D;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.position.Position;

public interface GridPattern {
	
	void init (Arene pArene);
	
	void paint (Graphics2D pGraphics);
	
	Position transform (Position pPosition);

}
