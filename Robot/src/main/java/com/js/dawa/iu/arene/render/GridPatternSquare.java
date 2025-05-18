package com.js.dawa.iu.arene.render;

import java.awt.Graphics2D;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.position.Position;

public class GridPatternSquare implements GridPattern {
	
    int mDecal = 40;
	
	int mSizeArene = 4;
	
	int mSizeCase = 20;
	
	@Override
	public void init(Arene pArene) {
		mSizeArene = pArene.getAreneProps().getSize();
		
	}

	/**
	 * print a square grid pattern
	 * with mSizeArene long size
	 */
	@Override
	public void paint(Graphics2D pGraphics) {
		int lSizeGrid = mSizeArene * mSizeCase;
		for (int li = 0; li < mSizeArene; li++) {
			//print number of range/column grid
			String lOrdonne = Integer.toString(li+1);
			pGraphics.drawString(lOrdonne, li * mSizeCase + mDecal+5, 0 +mDecal -5);
			pGraphics.drawString(lOrdonne,0 + (mDecal-20), li * mSizeCase + mDecal +12);
		}
		
		//draw horizontal line
		for (int li =0; li< mSizeArene+1;li++) {
			drawLineDecal (pGraphics,  li*mSizeCase,0,li*mSizeCase,lSizeGrid);
		}
		//draw vertical line
		for (int li =0; li< mSizeArene+1;li++) {
			drawLineDecal (pGraphics,0,li*mSizeCase,lSizeGrid,li*mSizeCase);
			
		}
		
	}
	
	void drawLineDecal (Graphics2D pGraphics,int x1,int y1, int x2, int y2) {
		pGraphics.drawLine(x1+mDecal, y1+ mDecal, x2+mDecal, y2+ mDecal);
	}

	@Override
	public Position transform(Position pPosition) {
		double lx = (pPosition.getX() -1) * mSizeCase + mDecal ;
		double ly = (pPosition.getY() -1) * mSizeCase + mDecal ;
		return new Position(lx, ly);
	}



}
