package com.js.dawa.prog.instruction;

import java.util.ArrayList;
import java.util.List;

import com.js.dawa.model.arene.ObjetArene;

public class ArgString {
	
	static String JS ="JS:";
	
	static String DEBVAR ="$";
	
	static String RANDOM ="Rand";
	
	private List<String> mLstArgs  = new ArrayList<>();
	
	ObjetArene mObjetArene;
	
	public ArgString ( ObjetArene pObjetArene,List<String> pLstArgs) {
		mLstArgs = pLstArgs;
		mObjetArene = pObjetArene;
	}
	
	public String getArgs (int pI) {
		String lVar = mLstArgs.get(pI);
		//manage variable
		CmdOnValue lCmdOnValue = null;
		if (lVar.startsWith(DEBVAR) && mObjetArene.getDataBoard() != null) {
			lCmdOnValue = new CmdOnValueVariable();
			
		}
		//manage Java Script eval
		else if (lVar.startsWith(JS)) {
			lCmdOnValue = new CmdOnValueJavaScript();
		}
		//manage random generation
		else if (lVar.startsWith(RANDOM)) {
			lCmdOnValue = new CmdOnValueRandom();
		}
		
		if (lCmdOnValue != null) {
			lCmdOnValue.init(mObjetArene);
			lVar = lCmdOnValue.computeVal(lVar);
		}
				
		
		
		return  lVar;
	}

}
