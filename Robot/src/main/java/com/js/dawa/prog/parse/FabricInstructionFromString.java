package com.js.dawa.prog.parse;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.lang3.StringUtils;

import com.js.dawa.prog.instruction.Instruction;
import com.js.dawa.util.DawaException;

public class FabricInstructionFromString {
	
	
	Instruction createInstance (String pClassName) throws DawaException {
		
		Instruction lIns;
		try {
			 Class<?> lClass = Class.forName("com.js.dawa.prog.instruction.Ins" + StringUtils.capitalize(pClassName));
			 lIns = (Instruction) lClass.getDeclaredConstructor().newInstance();
		} catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException |InstantiationException e) {
			throw new DawaException("error instance", e);
		}
		 
		 return lIns;

        
	}

}
