package com.js.dawa.prog.parse;

import com.js.dawa.util.DawaException;
import com.js.dawa.util.In;

public class ParseInstructionFile {
	
	
	
	

	
	void parse (String pPath) throws DawaException{
		In lIn = new In();
		lIn.open(pPath);
		
		
		lIn.close();
	}
	


}
