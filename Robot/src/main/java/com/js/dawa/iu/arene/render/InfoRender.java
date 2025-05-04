package com.js.dawa.iu.arene.render;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class InfoRender {
	
	private String mString;
	private String mColor;
	private ColorRender mColorRender = new ColorRender();
	private String mFont ="PLAIN";
	private int mSizePolice =14;
	private List<String> mDecorteur;
	
	
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
		return mColorRender.getColorForAwt(mColor);
	}
	

	
	public int getFontAwt() {
		return FontRenderForAwt.getFont(mFont);
	}
	
	
	public void setString(String pString) {
		this.mString = pString;
	}
	public String getColor() {
		return mColor;
	}
	public void setColor(String pColor) {
		mColorRender.reset();
		this.mColor = pColor.toLowerCase();
	}
	
	public void setFont (String pFont) {
		mFont = pFont;
	}
	
	
	public String getFont () {
		return mFont;
	}

	public int getSizePolice() {
		return mSizePolice;
	}

	public void setSizePolice(int pSizePolice) {
		this.mSizePolice = pSizePolice;
	}
	
	
	public List<String> getDecorateur() {
		return mDecorteur;
	}
	
	public void addDecorateur (String pDecorateur) {
		if (mDecorteur==null) {
			mDecorteur = new ArrayList<>();
			
		}
		mDecorteur.add(pDecorateur);
	}
	
	public String getColorWeb() {
		return mColorRender.convertToHex();
	}
	
	

}
