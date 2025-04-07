package com.js.dawa.iu.graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JLabel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.robot.Position;

public class UIPanel extends JLabel {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( UIPanel.class );
	
	transient Graphics mg;
	
	int mDecal = 75;
	
	int mSizeArene = 3;
	
	int lSizeCase = 20;
	

	transient  Arene mArene;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public void init (Arene pArene) {
		mArene = pArene;
		mSizeArene = pArene.getAreneProps().getSize();
		

	}
	
	@Override	
	public void paint (Graphics pg) {
		super.paint(pg);
		mg = pg;
	
		int lSizeGrid = mSizeArene * lSizeCase;
		
		
		pg.setColor(Color.GRAY);
		
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
		List<ModuleArena >lLstCase = mArene.getLstCase();
		
		//Affiche Case (robot and objet)
		LOGGER.debug("Tot objet {}",lLstCase.size());
		for (ModuleArena lModuleArene : lLstCase) {
			print(lModuleArene.getObjetArene());
		}
			
	}
	
	
	void print (ObjetArene pObjetArene) {
		Position lPos = pObjetArene.getPosition();
		int lx = (lPos.getX() -1)* lSizeCase + mDecal + 6;
		int ly = (lPos.getY() -1) * lSizeCase + mDecal +15;
		for (CaseRender lRender : pObjetArene.getRender() ) {
			InfoRender lInfoRender = lRender.getInfoRender();
			mg.setColor(lInfoRender.getColorForAwt());
			mg.drawString(lInfoRender.getString(), lx, ly);
		}
		//remove secondary Render
		pObjetArene.getRender().removeIf( n -> n.isSecondary());
	}
	
	
	void drawLineDecal (int x1,int y1, int x2, int y2) {
		mg.drawLine(x1+mDecal, y1+ mDecal, x2+mDecal, y2+ mDecal);
	}
	
	
	
	
	
	

	
	

}
