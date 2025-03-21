package com.js.dawa.iu.arene;


import java.util.HashMap;

import java.util.Map;

import com.js.dawa.iu.arene.render.CaseAreneRender;
import com.js.dawa.iu.arene.render.CaseAreneRenderEmpty;
import com.js.dawa.iu.console.Console;

public class Arene {
	
	int mTotCoteCase = 4;
	int mTotX = 100;
	int mTotY = 100;
	Console mConsole;	
	
	Map<Integer,CaseArene> mLstCaseArene = new HashMap<>();
	
	public Arene (Console pConsole) {
		mConsole = pConsole;
	}
	
	public void printArene () {
		for (int li = 0; li < mTotY; li++) {
			
			if (li==0) {
				
				//mConsole.printHeader("   ",mTotY);
			}
		    //	mConsole.printText(pad(Integer.toString(li)) + " ");
			for (int lj = 0; lj < mTotX; lj++) {
				int lVal = li*mTotY + lj;
					CaseAreneRender lRender = new CaseAreneRenderEmpty();
					CaseArene lCase = mLstCaseArene.get(Integer.valueOf(lVal));
					
					if (lCase != null) {
						  lRender= lCase.getRender(); 
					}
				
				
				mConsole.printRender(lRender);
				
				
			}
			mConsole.rc();
		}
	}
	
	public void addCaseArene (int pX, int pY,CaseArene pCaseArene) {
		int lPos = pY * mTotY + pX;
		mLstCaseArene.put(Integer.valueOf(lPos),pCaseArene);
	}
	
	String pad (String pVal) {
		String lRes = pVal;
		if (pVal.length() < 2) {
			lRes = "0" + pVal;
		}
		
		return lRes;
	}
	


}
