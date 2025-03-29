package com.js.dawa.ui.graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.iu.arene.Arene;
import com.js.dawa.iu.arene.ModuleArena;
import com.js.dawa.iu.arene.ObjetArene;
import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.robot.model.Position;

public class UIPanel extends JPanel {
	
	private static final Logger LOGGER =  LogManager.getLogger( UIPanel.class );
	
	Graphics mg;
	
	int mDecal = 100;
	
	int mSizeArene = 3;
	
	int lSizeCase = 20;
	
	Arene mArene;
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
		List<ModuleArena >lLstCase = mArene.getLstCase();
		
		//Affiche Case (robot and objet)
		LOGGER.info("Tot objet {}",lLstCase.size());
		for (ModuleArena lModuleArene : lLstCase) {
			print(lModuleArene.getObjetArene());
		}
			
	}
	
	
	void print (ObjetArene pObjetArene) {
		LOGGER.info("objet Arene {}",pObjetArene);
		Position lPos = pObjetArene.getPosition();
		int lx = (lPos.getX() -1)* lSizeCase + mDecal + 5;
		int ly = (lPos.getY() -1) * lSizeCase + mDecal +12;
		InfoRender lInfoRender = pObjetArene.getRender().getInfoRender();
		mg.setColor(lInfoRender.getColorForAwt());
		mg.drawString(lInfoRender.getString(), lx, ly);
	}
	
	
	void drawLineDecal (int x1,int y1, int x2, int y2) {
		mg.drawLine(x1+mDecal, y1+ mDecal, x2+mDecal, y2+ mDecal);
	}
	
	
	

	
	

}
