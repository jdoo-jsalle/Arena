package com.js.dawa.iu.arene.render;

import java.awt.Color;

public class InfoRender {
	
	private String mString;
	private String mColor;
	
	
	public InfoRender (String pString){
		mString = pString;
	}
	
	public InfoRender () {
		//na
	}
	
	public String getString() {
		return mString;
	}
	
	
	public Color getColorForAwt () {
		if (mColor == null) {
			return Color.BLUE;
		}
		else if (mColor.equals("red")) {
			return Color.RED;
			
		}
		else if (mColor.equals("pink")) {
			return Color.PINK;
			
		}
		else if (mColor.equals("cyan")) {
			return Color.CYAN;
			
		}
		else if (mColor.equals("gray")) {
			return Color.GRAY;
			
		}
		else if (mColor.equals("dark_gray")) {
			return Color.DARK_GRAY;
			
		}
		else if (mColor.equals("yellow")) {
			return Color.YELLOW;
			
		}
		else return Color.BLACK;
		
	}
	
	public void setString(String pString) {
		this.mString = pString;
	}
	public String getColor() {
		return mColor;
	}
	public void setColor(String pColor) {
		this.mColor = pColor.toLowerCase();
	}

}
