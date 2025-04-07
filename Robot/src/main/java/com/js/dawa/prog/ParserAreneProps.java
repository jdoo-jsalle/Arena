package com.js.dawa.prog;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.model.robot.RobotsProps;
import com.js.dawa.util.DawaException;
import com.js.dawa.util.In;

public class ParserAreneProps {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( ParserAreneProps.class );
	
	static String FILE_PARA_MALFORMED = "File param is malformed";
	
	List<ModuleArena> mLstModuleArena = new ArrayList<>();
	
	void parseAreneProps (String pPath) throws DawaException {
		
		try (In lIn = new In()){
			
			lIn.open(pPath);
			String lLigne = lIn.readLine();
			
			ModuleArena lCurrentModuleArena = null;
			Robot lCurrentRobot =null;
			
			while (lLigne != null) {
				lLigne = lLigne.trim();
				
				if (lLigne.equals("[Robot]")) {
					lCurrentModuleArena = new ModuleArena();
					lCurrentModuleArena.setIsRobot();
					mLstModuleArena.add(lCurrentModuleArena);
					Robot lRobot = new Robot();
					RobotsProps lRobotProps = new RobotsProps();
					lRobot.init(lRobotProps);
					lCurrentRobot = lRobot;
					lCurrentModuleArena.setObjetArene(lRobot);
				}
				else if (lLigne.startsWith("Name:")) {
					if (lCurrentRobot == null) throw new DawaException (FILE_PARA_MALFORMED);
					lCurrentRobot.getRobotProps().setName(getProperties(lLigne));
				}
				else if (lLigne.startsWith("Color:")) {
					if (lCurrentRobot == null) throw new DawaException (FILE_PARA_MALFORMED);
					lCurrentRobot.getRobotProps().setColor(getProperties(lLigne));
				}
				else if (lLigne.startsWith("Prg:")) {
					if (lCurrentModuleArena == null) throw new DawaException (FILE_PARA_MALFORMED);
					lCurrentModuleArena.setNamePrg(getProperties(lLigne));
				}
				else {
					LOGGER.info("Ligne {} not processed",lLigne);
				}
				
				
				lLigne = lIn.readLine();//new line
			}
			
			
			
		}
	}
	
	
	
	
	String getProperties (String pLigne) {
		int lPos = pLigne.indexOf(":");
		return pLigne.substring(lPos+1);
	}
	
	public String toString () {
		return mLstModuleArena.toString();
	}

}
