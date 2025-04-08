package com.js.dawa.iu.arene.render;

import java.awt.Color;
import java.lang.reflect.Field;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class InfoRender {
	
	private String mString;
	private String mColor;
	private Color mColorAwt;
	
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
				Field field = Class.forName("java.awt.Color").getField(mColor.toLowerCase());
				
				mColorAwt = (Color)field.get(null);
			} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException 
					|  NoSuchFieldException| SecurityException  e) {
					LOGGER.debug("error",e);
					mColorAwt = Color.black;
			}
		}
		 
		 return mColorAwt;

	        
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
	
	
	

}
