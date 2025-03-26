package com.js.dawa.ui.graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.iu.arene.AreneProps;
import com.js.dawa.iu.arene.ObjetArene;
import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.robot.model.Position;

public class UIPanel extends JPanel {
	
	private static final Logger LOGGER =  LogManager.getLogger( UIPanel.class );
	
	Graphics mg;
	
	int mDecal = 100;
	
	int mSizeArene = 3;
	
	int lSizeCase = 20;
	
	List<ObjetArene > mLstCase;	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void init (AreneProps pAttribut) {
		mSizeArene = pAttribut.getSize();
	}
	
	@Override	
	public void paint (Graphics pg) {
		super.paint(pg);
		mg = pg;
	
		int lSizeGrid = mSizeArene * lSizeCase;
		
		
		pg.setColor(Color.BLUE);
		
		for (int li = 0; li < mSizeArene; li++) {
			String lOrdonne = Integer.toString(li+1);
			pg.drawString(lOrdonne, li * lSizeCase + mDecal+5, 0 +mDecal -5);
			pg.drawString(lOrdonne,0 + (mDecal-20), li * lSizeCase + mDecal +12);
		}
		
		for (int li =0; li< mSizeArene+1;li++) {
			drawLineDecal (li*lSizeCase,0,li*lSizeCase,lSizeGrid);
		}
		
		for (int li =0; li< mSizeArene+1;li++) {
			drawLineDecal (0,li*lSizeCase,lSizeGrid,li*lSizeCase);
			
		}
		//Affiche Case
		
		for (ObjetArene lObjetArene : mLstCase) {
			LOGGER.info("objet Arene {}",lObjetArene);
			Position lPos = lObjetArene.getPosition();
			int lx = (lPos.getX() -1)* lSizeCase + mDecal + 5;
			int ly = (lPos.getY() -1) * lSizeCase + mDecal +12;
			InfoRender lInfoRender = lObjetArene.getRender().getInfoRender();
			pg.setColor(lInfoRender.getColorForAwt());
			pg.drawString(lInfoRender.getString(), lx, ly);
			
			
		}
		
		
		
	}
	
	void drawLineDecal (int x1,int y1, int x2, int y2) {
		mg.drawLine(x1+mDecal, y1+ mDecal, x2+mDecal, y2+ mDecal);
	}
	
	
	
	public void setLstCase (List <ObjetArene > pLstCase) {
		mLstCase = pLstCase;
	}
	
	

}
