package com.js.dawa.iu.graphique;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.GridPattern;
import com.js.dawa.iu.arene.render.GridPatternSquare;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.robot.Position;

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

		g2.setFont(getFont());
		
		g2.setColor(Color.gray);
			
		
		mGridPattern.paint(g2);
		
		List<ModuleArena >lLstCase = mArene.getLstCaseMain();
		
		//Affiche Case (robot and objet)
		LOGGER.debug("Tot objet {}",lLstCase.size());
		for (ModuleArena lModuleArene : lLstCase) {
			print(lModuleArene.getObjetArene());
		}
		
		pg.drawImage(buffer, 0, 0, this);
		g2.dispose();
	
		
			
	}
	
	
	void print (ObjetArene pObjetArene) {
		Position lPos = pObjetArene.getPosition();
		LOGGER.debug("Post object {}",lPos);

		Position lTranslate = mGridPattern.transform(lPos);
		
		for (CaseRender lRender : pObjetArene.getRender() ) {
			lRender.paint(g2, lTranslate.getX(), lTranslate.getY());
		}
		//remove secondary Render
		pObjetArene.getRender().removeIf( n -> n.isSecondary());
	}
	
	

	
	
	
	
	
	

	
	

}
