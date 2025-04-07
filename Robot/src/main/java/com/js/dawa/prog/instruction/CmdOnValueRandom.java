package com.js.dawa.prog.instruction;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.model.arene.ObjetArene;

public class CmdOnValueRandom implements CmdOnValue {
	private static final Logger LOGGER =  LoggerFactory.getLogger( CmdOnValueRandom.class );
	ObjetArene mObjetArene;
	static Random randomNumbers = new Random();

	@Override
	public void init(ObjetArene pObjetArene) {
		mObjetArene = pObjetArene;

	}
	
	
	int getArgsRandom (String pClause) {
		int lStart = pClause.indexOf("[");
		int lEnd = pClause.indexOf("]");
		String lVal = pClause.substring(lStart+1,lEnd).trim();
		LOGGER.debug("Args Random : \"{}\"", lVal);
		return Integer.parseInt(lVal);

	
	
		
	}
	

	@Override
	public String computeVal(String pVal) {
		String lClause = pVal.substring(ArgString.RANDOM.length());
		
		
		int lArg = getArgsRandom(lClause);
		int lVal = randomNumbers.nextInt(lArg);
		int lSigne = randomNumbers.nextInt(2);//on assigne un chiffre négatif au hasard
		if (lSigne == 0) lVal = lVal * -1;
		LOGGER.debug("Args generate : \"{}\"", lVal);
		return Integer.toString(lVal);
	}

}
