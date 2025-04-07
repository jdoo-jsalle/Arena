package com.js.dawa.prog.parse;

public class LignePrg {

	
	/**
	 * rm comment from ligne 
	 * and trim it.
	 */
	String getValue (String pLigne) {
		String lRes = pLigne;
		if (pLigne != null) {
			//seek begin of comment
			int lPos = pLigne.indexOf("//");
			//remove it
			if (lPos != -1)
				lRes = pLigne.substring(0,lPos);
			lRes = lRes.trim();
		}
		
		return lRes;
	}
}
