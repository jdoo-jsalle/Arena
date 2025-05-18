package com.js.dawa.iu.arene.render;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;

import com.js.dawa.model.position.Position;

public class RenderString {
	
	
	static int decalX = 6;//for center letter
	static int decalY = 15;
	
	public void paint (Graphics2D pGraphics, Position pPosition, InfoRender pInfoRender) {
		Position lPosition = pPosition;
		lPosition.addXY(decalX, decalY);
		int lVal = pInfoRender.getFontAwt() ;
		if (lVal!= 0) {
			pGraphics.setFont(pGraphics.getFont().deriveFont(lVal,pInfoRender.getSizePolice()));
		}
		
		pGraphics.setColor(pInfoRender.getColorForAwt());
		pGraphics.drawString(pInfoRender.getString(), lPosition.getXi(), lPosition.getYi());
		List<String> lVals= pInfoRender.getDecorateur();
		//add other decorateur
		if (lVals != null) {
			for(String lDec : lVals) {
				pGraphics.drawString(lDec, lPosition.getXi(), lPosition.getYi());
			}
		}
		
		if (lVal!= 0) {
			pGraphics.setFont(pGraphics.getFont().deriveFont(Font.PLAIN,14));
		}
	}

}
