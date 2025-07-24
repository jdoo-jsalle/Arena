package com.js.dawa;

import java.util.ArrayList;
import java.util.List;

import com.js.dawa.model.arene.ModuleArena;

/**
 * Classe qui gère la liste des informations de fin de partie pour tous les robots.
 * Permet d'ajouter les résultats de chaque robot et de fournir un résumé du classement.
 */
public class ListInfoEnd {
	
	/** Liste des informations de fin de partie pour chaque robot */
	List<InfoEnd> mLstEnd = new ArrayList<>();
	
	/**
	 * Ajoute les informations de fin pour un robot donné si c'est bien un robot.
	 * @param pModuleArena Le module (robot) concerné
	 * @param pTour Le tour où il a été éliminé ou a gagné
	 */
	void addInfo (ModuleArena pModuleArena, int pTour) {
		if (pModuleArena.isRobot()) {
			InfoEnd lInfoEnd = new InfoEnd();
			lInfoEnd.mModule = pModuleArena;
			lInfoEnd.mTour = pTour;
			mLstEnd.add(lInfoEnd);
		}
	}
	
	/**
	 * Retourne le nom du gagnant (à implémenter si besoin)
	 * @return Nom du gagnant ou chaîne vide
	 */
	public String getWinner () {
		return "";
	}
	
	/**
	 * Retourne la liste des résultats de fin de partie sous forme de chaîne.
	 * @return Résumé du classement
	 */
	public String toString () {
		return mLstEnd.toString();
	}

}
