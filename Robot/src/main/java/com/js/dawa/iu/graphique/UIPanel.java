package com.js.dawa.iu.graphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.GridPattern;
import com.js.dawa.iu.arene.render.GridPatternSquare;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.position.Position;

public class UIPanel extends JPanel{
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( UIPanel.class );
	
	
	private transient BufferedImage buffer;
	private transient Graphics2D g2;
	
	
	transient GridPattern mGridPattern = new GridPatternSquare();

	transient  Arene mArene;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L; 
	
	
	
	
	public void init (Arene pArene) {
		mArene = pArene;
		mGridPattern.init(pArene);
	

	}
	
	@Override	
	public void paint (Graphics pg) {
		LOGGER.debug("UIPanel.paint");
		super.paint(pg);
		
		
		if (buffer == null) {
			buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }
		//clear
		g2 = buffer.createGraphics();  
	
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		
		g2.setColor(Color.gray);
			
		
		mGridPattern.paint(g2);
		

		//Affiche Case (robot and objet)
		LOGGER.debug("Tot objet {}", mArene.getLstCaseMain().size());
		synchronized (mArene.getLstCaseMain()) {
			for (ModuleArena lModuleArene : mArene.getLstCaseMain()) {
				print(lModuleArene.getObjetArene());
			}
	     }
		
		pg.drawImage(buffer, 0, 0, this);
		g2.dispose();
	
		
			
	}
	
	
	void print (ObjetArene pObjetArene) {
		Position lPos = pObjetArene.getPositionScreen();
		Position lTranslate = mGridPattern.transform(lPos);
		LOGGER.debug("Pos object {}  => trans {}" , lPos ,lTranslate);
		
		for (CaseRender lRender : pObjetArene.getRender() ) {
			lRender.paint(g2, lTranslate);
		}
		//remove obsolete Render
		pObjetArene.getRender().removeIf( n -> n.isObsolete());
	}
	 
	

	
	
	
	
	
	

	
	

}
