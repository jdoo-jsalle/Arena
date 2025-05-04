package com.js.dawa.iu.arene.render;

import java.awt.Color;
import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColorRender {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( ColorRender.class );
	
	
	private String mColor;
	
	private Color mColorAwt;
	
	
	
	public void setColor(String pColor) {
		mColor = pColor;
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
	
	
	public void reset() {
		mColorAwt = null;
	}
	
	public Color getColorForAwt (String pColor) {
		mColor = pColor;
		
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
	
	public String convertToHex () {
		getColorForAwt(mColor);
		
		return mColorAwt != null ? "#"+Integer.toHexString(mColorAwt.getRGB()).substring(2) : "#000000";
	}
	
	

}
