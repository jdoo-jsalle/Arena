package com.js.dawa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.iu.console.AffichageInfoRobot;
import com.js.dawa.iu.console.Console;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.util.DawaException;

/**
 * Classe responsable de l'exécution et de la boucle principale du moteur de simulation de l'arène.
 * Gère l'affichage, la progression des tours, l'exécution des programmes robots et l'affichage des résultats.
 */
public class EngineViewer {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( EngineViewer.class );
	
	// Liste des informations de fin de partie (classement, etc.)
	ListInfoEnd mLstInfoEnd = new ListInfoEnd();
	
	// Console d'affichage (texte ou graphique)
	Console mConsole;
	
	/**
	 * Lance la simulation de l'arène et la boucle principale.
	 * @param pArene L'arène à simuler
	 * @throws DawaException en cas d'erreur d'exécution
	 */
	public void execEngineViewer (Arene pArene) throws DawaException {
		pArene.getConsole().init(pArene);
		for (ModuleArena lModule : pArene.getLstCaseMain()) {// Initialisation des robots
			lModule.init();
		}
		engineCompute(pArene);
	}
	
	/**
	 * Boucle principale de la simulation : exécute les programmes, met à jour l'arène et affiche les résultats.
	 * @param pArene L'arène à simuler
	 * @throws DawaException en cas d'erreur d'exécution
	 */
	void engineCompute (Arene pArene) throws DawaException {
		boolean lEnd = false;
		mConsole = pArene.getConsole();
		mConsole.start();
		AffichageInfoRobot lAffichageInfoRobot = new AffichageInfoRobot(pArene);
		int lTour = 1;
		while (!lEnd) {
			LOGGER.debug("Main loop");
			try {
				Thread.sleep(100); // Pause entre chaque tour
			} catch (InterruptedException e) {
				LOGGER.error("Erreur thread", e);
				Thread.currentThread().interrupt();
			}
			// Exécution des programmes robots
			lEnd = !executePrg(lTour,pArene);
			pArene.updateListCase(); // Met à jour la liste des objets
			pArene.rmDisposeObjet(); // Supprime les objets obsolètes
			affichageCurrent(lTour, lAffichageInfoRobot, mConsole);
			lTour++;
		}
		LOGGER.info("Fin de la simulation");
		afficheEnd(mConsole); 
		mConsole.end();
	}
	
	/**
	 * Libère les ressources de la console à la fin de la simulation.
	 */
	public void dispose() {
		if (mConsole != null) {
			mConsole.close();
		}
	}
	
	/**
	 * Exécute les instructions de chaque robot pour un tour donné.
	 * @param pTour Numéro du tour
	 * @param pArene L'arène
	 * @return true si la simulation continue, false si elle est terminée
	 * @throws DawaException en cas d'erreur d'exécution
	 */
	boolean  executePrg (int pTour,Arene pArene) throws DawaException {
		int ltotRun = 0; 
		ModuleArena lLast = null;
		for (ModuleArena lModuleArena : pArene.getLstCaseMain()) {
			if (! lModuleArena.isOver()) {
				if (lModuleArena.isFonctionnel()) {
					if (lModuleArena.isRobot()) {
					    ltotRun ++;
					    lLast = lModuleArena;
					}
					lModuleArena.execInstruction();
				} else {
					lModuleArena.setOver(true);
					mLstInfoEnd.addInfo(lModuleArena,pTour);
				}
			}
		}
		boolean lContinue = ltotRun > 1; // Continue tant qu'il reste plus d'un robot
		if (!lContinue) {
			mLstInfoEnd.addInfo(lLast,pTour+1);
		}
		return lContinue;
	}
	
	/**
	 * Affiche l'état courant de la simulation dans la console.
	 * @param pTour Numéro du tour
	 * @param pAffichageInfoRobot Affichage des infos robots
	 * @param pConsole Console d'affichage
	 */
	void affichageCurrent (int pTour,AffichageInfoRobot pAffichageInfoRobot, Console pConsole ) {
		StringBuilder lText = new StringBuilder();
		lText.append("<html>");
		lText.append("Step : " + Integer.toString(pTour));
		lText.append("<br>");
		lText.append(pAffichageInfoRobot.getAffichageInfoRobot());
		lText.append("<br>");
		lText.append("</html>");
		pConsole.setText(lText.toString());
	}
	
	/**
	 * Affiche le classement final à la fin de la simulation.
	 * @param pConsole Console d'affichage
	 */
	void afficheEnd ( Console pConsole ) {
		StringBuilder lText = new StringBuilder();
		lText.append("<html>");
		lText.append("La partie est terminée, classement :");
		lText.append("<br>");
		lText.append(mLstInfoEnd.toString());
		lText.append("<br>");
		lText.append("</html>");
		pConsole.setText(lText.toString());
	}

}
