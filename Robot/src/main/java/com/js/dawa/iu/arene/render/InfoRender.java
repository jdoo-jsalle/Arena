package com.js.dawa.iu.arene.render;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InfoRender {
	
	private String mString;
	private String mColor;
	private Color mColorAwt;
	private String mFont ="PLAIN";
	private int mSizePolice =14;
	private List<String> mDecorteur;
	
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
	
	
	
	
	Color getFromRgb () {
		Color lRes = Color.black;
		int lPos1 = mColor.indexOf("[");
		int lPos0 = mColor.lastIndexOf("]");
		if (lPos0 != -1) {
			String [] lRgb = mColor.substring(lPos1+1,lPos0).split(",");
			if (lRgb.length ==3) {
				int[] lRgbf = new int[3];
				int l=0;
				for (String lF : lRgb) {
					try {
					   int lC = Integer.parseInt(lF);
					   if (lC <0 || lC > 255){
						   lC=0;
					   }
					   lRgbf[l]=lC;
					}
					catch (NumberFormatException le	) {
						lRgbf[l]=0;
						LOGGER.debug("error in translate color",le);
					}
					LOGGER.debug("val {} : {}",l,lRgbf[l]);
					l++;
				}
				lRes= new Color (lRgbf[0],lRgbf[1],lRgbf[2]);
			}
			
		}
		return lRes;
	}
	
	
	public Color getColorForAwt () {
		if (mColorAwt == null) {	
			
			LOGGER.debug("color {}",mColor);
			if (mColor.toUpperCase().startsWith("C[") ){
				
				mColorAwt = getFromRgb();
			}
			else
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
	
	
	public List<String> getDecorateur() {
		return mDecorteur;
	}
	
	public void addDecorateur (String pDecorateur) {
		if (mDecorteur==null) {
			mDecorteur = new ArrayList<>();
			
		}
		mDecorteur.add(pDecorateur);
	}
	
	

}
