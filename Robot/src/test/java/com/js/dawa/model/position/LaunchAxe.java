package com.js.dawa.model.position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LaunchAxe {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( LaunchAxe.class );
	
	public static void main(String[] args) {
		Position lPosition = new Position(0, 0);
		
		
		for (int li = 0; li < 8; li++) {
			LOGGER.info(lPosition.toString() + " " +lPosition.mAxe.getVector());
			lPosition.mAxe.doRotation(1);
		}
	}

}
