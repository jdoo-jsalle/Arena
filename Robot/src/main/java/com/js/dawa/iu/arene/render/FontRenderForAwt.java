package com.js.dawa.iu.arene.render;



public class FontRenderForAwt {
	
	
	private FontRenderForAwt() {
		//na
	}
	
	static int getFont (String pVal) {
		if (pVal.equals("PLAIN"))
			return 0;
		else if (pVal.equals("BOLD")) 
			return 1;
		else if (pVal.equals("ITALIC"))
			return 2;
		else 
			return 0;
	
	}

}
