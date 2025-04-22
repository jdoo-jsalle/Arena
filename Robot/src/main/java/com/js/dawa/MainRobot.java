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
 * Launch the MaintRobot.main whith dirvalue argument : -D
 */
public class MainRobot {
	
	
	String mDirectory;
	String mFileArena;
	
	static String ARENE_PROPERTIES = "Arene.properties";
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( MainRobot.class );
	
	public static void main(String[] args) {
		MainRobot lMainRobot = new MainRobot();
		try {
			lMainRobot.verifyArgs(args);
			lMainRobot.execAreneGame();
			
			
		} catch (ParseException | DawaException e) {
			LOGGER.error("Error Main",e);
		}
		
	}
	
	void verifyArgs (String[] pArgs) throws ParseException {
		
		Options lOptions = new Options();
		
		Option lFolder = Option.builder("D")
				.longOpt("directory")
				.argName("DIRECTORY")
				.desc("Folder for Robot properties file [Arene.properties + Robot*]")
				.hasArg()
				.required(true)
				.build();
		
		lOptions.addOption(lFolder);
		
		Option lFile = Option.builder("F")
				.longOpt("Arene*.properties")
				.argName("ARENA")
				.desc("Arena Properties File")
				.hasArg()
				.required(false)
				.build();
		
		lOptions.addOption(lFile);
		
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
		
		
		LOGGER.info("Directory is {} {}",mDirectory, mFileArena);
		
		
	}
	
	void execAreneGame () throws DawaException {
		ParserDirParams lPaserDireParams = new ParserDirParams();
		lPaserDireParams.parseDirParams(mDirectory,mFileArena);
		
		EngineViewer lEngineViewer = new EngineViewer();
		lEngineViewer.execEngineViewer(lPaserDireParams.getArene());
		
		JOptionPane.showConfirmDialog(null, 
	                "Game is over", "Arene", JOptionPane.DEFAULT_OPTION);
		lEngineViewer.dispose();
	}

	
	
	
	

}
