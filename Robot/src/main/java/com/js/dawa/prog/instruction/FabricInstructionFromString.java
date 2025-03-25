package com.js.dawa.prog.instruction;

import java.lang.reflect.InvocationTargetException;

import com.js.dawa.util.DawaException;

public class FabricInstructionFromString {
	
	
	Instruction createInstance (String pClassName) throws DawaException {
		
		Instruction lIns;
		try {
			 Class<?> lClass = Class.forName(pClassName);
			 lIns = (Instruction) lClass.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException |InstantiationException e) {
			throw new DawaException("error instance", e);
		}
		 
		 return lIns;

        
	}

}
