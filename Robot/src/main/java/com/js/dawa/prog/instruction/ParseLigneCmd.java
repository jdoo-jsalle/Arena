package com.js.dawa.prog.instruction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.util.DawaException;

public class ParseLigneCmd implements ParseLigne {
	 private static final Logger LOGGER =  LogManager.getLogger( ParseLigneCmd.class );
	
	
	public Args parse (int pNumLigne,String pLigne) throws DawaException {
		int lDeb = pLigne.indexOf("(");
		int lEnd = pLigne.indexOf(")");
		if (lDeb == -1 || lEnd == -1) {
			throw new DawaException("Ligne " + Integer.toString(pNumLigne) + " error must have param beetween ()");
		}
		String lInstruction = pLigne.substring(0,lDeb).trim();
		LOGGER.info("instruction : {}", lInstruction);
		Args lArgs = new Args();
		lArgs.setNameInstruction(lInstruction);
		
		String lParam = pLigne.substring(lDeb + 1, lEnd).trim();
		if (!lParam.isEmpty()) {
			LOGGER.info("Param : \"{}\"", lParam);
			String [] lParams = lParam.split(",",0);
			
			for (String lVal : lParams) {
				LOGGER.info("val : {}", lVal);
				lArgs.addArguments(lVal);
			}
		}
		
		return lArgs;
		
	}

}
