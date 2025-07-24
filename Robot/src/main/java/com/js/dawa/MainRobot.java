package com.js.dawa;

import javax.swing.JOptionPane;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.prog.ParserDirParams;
import com.js.dawa.util.DawaException;

/**
 * Classe principale de lancement du simulateur d'arène de robots.
 * Gère la récupération des arguments, l'initialisation de l'arène et le lancement du moteur graphique.
 */
public class MainRobot {
	
	// Dossier contenant les fichiers de configuration (Arene.properties, Robot*)
	String mDirectory;
	// Nom du fichier de configuration de l'arène
	String mFileArena;
	
	static String ARENE_PROPERTIES = "Arene.properties";
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( MainRobot.class );
	
	/**
	 * Point d'entrée principal du programme.
	 * @param args Arguments de la ligne de commande
	 */
	public static void main(String[] args) {
		MainRobot lMainRobot = new MainRobot();
		try {
			lMainRobot.verifyArgs(args);
			lMainRobot.execAreneGame();
		} catch (ParseException | DawaException e) {
			LOGGER.error("Erreur lors du lancement du programme",e);
		}
	}
	
	/**
	 * Vérifie et extrait les arguments de la ligne de commande.
	 * @param pArgs Arguments reçus
	 * @throws ParseException si les arguments sont invalides
	 */
	void verifyArgs (String[] pArgs) throws ParseException {
		Options lOptions = new Options();
		// Option pour le dossier de configuration
		Option lFolder = Option.builder("D")
				.longOpt("directory")
				.argName("DIRECTORY")
				.desc("Dossier contenant Arene.properties et les fichiers Robot*")
				.hasArg()
				.required(true)
				.build();
		lOptions.addOption(lFolder);
		// Option pour le fichier d'arène (optionnelle)
		Option lFile = Option.builder("F")
				.longOpt("Arene*.properties")
				.argName("ARENA")
				.desc("Fichier de configuration de l'arène")
				.hasArg()
				.required(false)
				.build();
		lOptions.addOption(lFile);
		// Parsing des arguments
		CommandLineParser parser = new DefaultParser();
		CommandLine cmd = parser.parse(lOptions, pArgs);
		mDirectory = cmd.getOptionValue("D");
		if (!mDirectory.endsWith("/")) {
			mDirectory = mDirectory + "/";
		}
		mFileArena = cmd.getOptionValue("F");
		if (mFileArena == null) {
			mFileArena = ARENE_PROPERTIES;
		}
		LOGGER.info("Dossier de configuration : {} Fichier arène : {}",mDirectory, mFileArena);
	}
	
	/**
	 * Lance le jeu d'arène avec les paramètres fournis.
	 * @throws DawaException en cas d'erreur d'initialisation
	 */
	void execAreneGame () throws DawaException {
		// Parse les fichiers de configuration
		ParserDirParams lPaserDireParams = new ParserDirParams();
		lPaserDireParams.parseDirParams(mDirectory,mFileArena);
		// Lance le moteur graphique
		EngineViewer lEngineViewer = new EngineViewer();
		lEngineViewer.execEngineViewer(lPaserDireParams.getArene());
		// Affiche une boîte de dialogue à la fin de la partie
		JOptionPane.showConfirmDialog(null, 
	                "La partie est terminée", "Arène", JOptionPane.DEFAULT_OPTION);
		lEngineViewer.dispose();
	}

}
