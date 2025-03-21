package com.js.dawa.ui.graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

import com.js.dawa.iu.arene.CaseArene;
import com.js.dawa.iu.arene.ObjetArene;
import com.js.dawa.robot.model.Position;

public class UIPanel extends JPanel {
	
	Graphics mg;
	
	int mDecal = 100;
	
	int lSizeArene = 30;
	
	int lSizeCase = 20;
	
	List<ObjetArene > mLstCase;	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override	
	public void paint (Graphics pg) {
		super.paint(pg);
		mg = pg;
	
		int lSizeGrid = lSizeArene * lSizeCase;
		
		
		pg.setColor(Color.BLUE);
		
		for (int li = 0; li < lSizeArene; li++) {
			String lOrdonne = Integer.toString(li+1);
			pg.drawString(lOrdonne, li * lSizeCase + mDecal+5, 0 +mDecal -5);
			pg.drawString(lOrdonne,0 + (mDecal-20), li * lSizeCase + mDecal +12);
		}
		
		for (int li =0; li< lSizeArene;li++) {
			drawLineDecal (li*lSizeCase,0,li*lSizeCase,lSizeGrid);
		}
		
		for (int li =0; li< lSizeArene+1;li++) {
			drawLineDecal (0,li*lSizeCase,lSizeGrid,li*lSizeCase);
			
		}
		//Affiche Case
		
		for (ObjetArene lObjetArene : mLstCase) {
			
			Position lPos = lObjetArene.getPosition();
			int lx = (lPos.getX() -1)* lSizeCase + mDecal + 5;
			int ly = (lPos.getY() -1) * lSizeCase + mDecal +12;
			pg.drawString(lObjetArene.getRender().getStringRender(), lx, ly);
		}
		
		
		
	}
	
	void drawLineDecal (int x1,int y1, int x2, int y2) {
		mg.drawLine(x1+mDecal, y1+ mDecal, x2+mDecal, y2+ mDecal);
	}
	
	
	
	public void setLstCase (List <ObjetArene > pLstCase) {
		mLstCase = pLstCase;
	}
	
	

}
