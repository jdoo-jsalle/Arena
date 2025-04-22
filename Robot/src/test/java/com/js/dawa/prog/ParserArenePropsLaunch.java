package com.js.dawa.prog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.util.DawaException;

public class ParserArenePropsLaunch {

	private static final Logger LOGGER =  LoggerFactory.getLogger( ParserArenePropsLaunch.class );
	
	public static void main(String[] args) {
		ParserAreneProps lParserAreneProps  = new ParserAreneProps();
		try {
			lParserAreneProps.parseAreneProps("./src/main/resources/Arene.properties");
			LOGGER.info("LstModule {}",lParserAreneProps);
		} catch (DawaException e) {
			LOGGER.error("Error",e);
		}
	}
}
