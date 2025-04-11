package com.js.dawa.iu.arene.render;

import java.awt.Color;
import java.lang.reflect.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InfoRender {
	
	private String mString;
	private String mColor;
	private Color mColorAwt;
	private String mFont ="PLAIN";
	private int mSizePolice =14;
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( InfoRender.class );
	
	
	
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
		if (mColorAwt == null) {	
		
			try {
				Field field = Class.forName("java.awt.Color").getField(mColor.toUpperCase());
				
				mColorAwt = (Color)field.get(null);
			} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException 
					|  NoSuchFieldException| SecurityException  e) {
					LOGGER.debug("error with color \"" + mColor +"\"",e);
					mColorAwt = Color.black;
			}
		}
		 
		 return mColorAwt;

	        
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
		mColorAwt = null;
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
	
	
	

}
