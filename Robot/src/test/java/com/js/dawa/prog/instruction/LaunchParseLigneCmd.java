package com.js.dawa.prog.instruction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.js.dawa.util.DawaException;

public class LaunchParseLigneCmd {
	
	 private static final Logger LOGGER =  LogManager.getLogger( LaunchParseLigneCmd.class );
	
	public static void main(String[] args) {
		ParseLigneCmd lParseLigneCmd = new ParseLigneCmd();
		
		
		try {
			lParseLigneCmd.parse(0, "truc (0,1)");
			lParseLigneCmd.parse(1, "if (truc=0)");
			lParseLigneCmd.parse(2, "    machin (0,2)");
			lParseLigneCmd.parse(3, "    bidule (1,1)");
			lParseLigneCmd.parse(4, "    yup (0,1)");
			lParseLigneCmd.parse(5, "else");
			lParseLigneCmd.parse(6, "    machin_1 (0,2)");
			lParseLigneCmd.parse(1, "    if (truc=4)");
			lParseLigneCmd.parse(30,"         io (1,1,1,x,1,)");
			lParseLigneCmd.parse(30,"         iop (1,1,1,x,1,)");
			lParseLigneCmd.parse(9, "    endif");
			lParseLigneCmd.parse(7, "    bidule_1 (1,1)");
			lParseLigneCmd.parse(8, "    yup_1 (0,1)");
			lParseLigneCmd.parse(9, "endif");
			LOGGER.info("");
			lParseLigneCmd.mMainLstInstruction.dump("");
			
			
		} catch (DawaException e) {
			LOGGER.error("error", e);
		}
		
	}

}
