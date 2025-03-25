package com.js.dawa.prog.instruction;

import com.js.dawa.util.DawaException;

public interface ParseLigne  {
	
	public Args parse (int pNumLigne,String pLigne) throws DawaException;

}
