package com.js.dawa.prog.parse;

public class LignePrg {

	
	private boolean mSkipable;
	
	/**
	 * rm comment from ligne 
	 * and trim it.
	 */
	String getValue (String pLigne) {
		mSkipable = false;
		String lRes = pLigne;
		if (pLigne != null) {
			//seek begin of comment
			int lPos = pLigne.indexOf("//");
			//remove it
			if (lPos != -1)
				lRes = pLigne.substring(0,lPos);
			lRes = lRes.trim();
		}
		verify (lRes);
		
		return lRes;
	}
	
	void verify (String pVal){
		mSkipable = pVal == null || pVal.isEmpty() || pVal.startsWith("#") || pVal.contains("loop");
	}
	
	boolean isSkipable () {
		return mSkipable;
	}
}
