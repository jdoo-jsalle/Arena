package com.js.dawa.iu.arene.render;

import java.awt.Font;
import java.awt.Graphics2D;

import com.js.dawa.model.position.Position;

public class RenderString {
	
	public void paint (Graphics2D pGraphics, Position pPosition, InfoRender pInfoRender) {
		int lVal = pInfoRender.getFontAwt() ;
		if (lVal!= 0) {
			pGraphics.setFont(pGraphics.getFont().deriveFont(lVal,pInfoRender.getSizePolice()));
		}
		
		pGraphics.setColor(pInfoRender.getColorForAwt());
		pGraphics.drawString(pInfoRender.getString(), pPosition.getXi(), pPosition.getYi());
		
		if (lVal!= 0) {
			pGraphics.setFont(pGraphics.getFont().deriveFont(Font.PLAIN,14));
		}
	}

}
