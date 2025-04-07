package com.js.dawa.prog;

import java.io.File;

import com.js.dawa.util.DawaException;

public class ParserDirParams {
	
	static String ARENE_PROPERTIES = "Arene.properties";
	
	/**
	 * Reader dir params
	 *   seek : Arene.propeties
	 *   seek : Robot*
	 * @param pPath
	 * @throws DawaException
	 */
	public void parseDirParams (String pPath) throws DawaException{
		File lDir = new File (pPath);
		if (lDir.isDirectory()) {
			File lFile = new File (pPath + File.pathSeparatorChar + ARENE_PROPERTIES);
			if (lFile.exists()) {
				ParserAreneProps lParserAreneProps = new ParserAreneProps();
				lParserAreneProps.parseAreneProps(lFile.getAbsolutePath());
			}
			else {
				throw new DawaException("File " + ARENE_PROPERTIES + " not exist in " + pPath);
			}
			
			
		}
		else {
			throw new DawaException("Dir " + pPath + " is not valid path directory");
		}
	}

}
