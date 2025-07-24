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
import com.js.dawa.iu.arene.render.GridPatternHex;
import com.js.dawa.iu.arene.render.GridPatternSquare;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.position.Position;

/**
 * UIPanel : panneau graphique principal pour l'affichage de l'arène et des robots.
 * Gère le rendu de la grille, des objets et des robots sur un buffer pour éviter le scintillement.
 */
public class UIPanel extends JPanel implements ManageBuffer{
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( UIPanel.class );
	
	// Buffer d'image pour le rendu hors écran (double buffering)
	private transient BufferedImage buffer;
	private transient Graphics2D g2;
	
	// Grille utilisée pour l'affichage (hexagonale par défaut)
	transient GridPattern mGridPattern = new GridPatternSquare();

	// Référence à l'arène à afficher
	transient  Arene mArene;
	/**
	 * Numéro de version pour la sérialisation (non utilisé ici mais requis par JPanel)
	 */
	private static final long serialVersionUID = 1L; 
	
	/**
	 * Initialise le panneau avec l'arène à afficher et la grille.
	 * @param pArene l'arène à afficher
	 */
	public void init (Arene pArene) {
		mArene = pArene;
		mGridPattern.init(pArene);
	}
	
	
	public void updateBuffer () {
		// Création du buffer si nécessaire
		if (buffer == null) {
			buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
        }
		// Efface le buffer
		g2 = buffer.createGraphics();  
		g2.fillRect(0, 0, getWidth(), getHeight());
		
		// Active l'antialiasing pour un rendu plus lisse
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// Police par défaut
		g2.setFont(new Font("TimesRoman", Font.PLAIN, 12));
		g2.setColor(Color.gray);
		
		// Dessine la grille
		mGridPattern.paint(g2);
		
		// Affiche chaque case contenant un objet (robots, etc.)
		LOGGER.debug("Tot objet {}", mArene.getLstCaseMain().size());
		synchronized (mArene.getLstCaseMain()) {
			for (ModuleArena lModuleArene : mArene.getLstCaseMain()) {
				print(lModuleArene.getObjetArene());
			 }
	     }
		
		g2.dispose();
		
	}
	
	/**
	 * Méthode de rendu principale appelée par Swing.
	 * Utilise un buffer pour dessiner la grille et les objets, puis affiche le buffer à l'écran.
	 * @param pg le contexte graphique fourni par Swing
	 */
	@Override	
	protected void paintComponent (Graphics pg) {
		LOGGER.debug("UIPanel.paint");
		super.paintComponent(pg);
		
		updateBuffer();
		// Affiche le buffer à l'écran
		pg.drawImage(buffer, 0, 0, this);
	
	}
	
	/**
	 * Affiche un objet de l'arène (robot, énergie, mine, etc.) à sa position transformée par la grille.
	 * @param pObjetArene l'objet à afficher
	 */
	void print (ObjetArene pObjetArene) {
		// Récupère la position logique de l'objet
		Position lPos = pObjetArene.getPositionScreen();
		// Transforme la position selon la grille (hexagonale, etc.)
		Position lTranslate = mGridPattern.transform(lPos);
		LOGGER.debug("Pos object {}  => trans {}" , lPos ,lTranslate);
		// Dessine chaque rendu associé à l'objet (il peut y en avoir plusieurs)
		for (CaseRender lRender : pObjetArene.getRender() ) {
			lRender.paint(g2, lTranslate);
		}
		// Supprime les rendus obsolètes (ex : effets temporaires)
		pObjetArene.getRender().removeIf( n -> n.isObsolete());
	}
	 
	// Fin de la classe UIPanel
}
