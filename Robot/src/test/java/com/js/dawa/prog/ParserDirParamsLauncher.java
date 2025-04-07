package com.js.dawa.prog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.util.DawaException;

public class ParserDirParamsLauncher {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( ParserDirParamsLauncher.class );
	
	public static void main(String[] args) {
		ParserDirParams lPaserDireParams = new ParserDirParams();
		try {
			lPaserDireParams.parseDirParams("./src/main/resources/");
		} catch (DawaException e) {
		    LOGGER.error("Error",e);
		}
	}

}
