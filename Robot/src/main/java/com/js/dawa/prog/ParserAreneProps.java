package com.js.dawa.prog;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.iu.console.ConsoleGraphique;
import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.AreneProps;
import com.js.dawa.model.arene.Energie;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.robot.Position;
import com.js.dawa.model.robot.Robot;
import com.js.dawa.model.robot.RobotsProps;
import com.js.dawa.util.DawaException;
import com.js.dawa.util.In;

public class ParserAreneProps {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( ParserAreneProps.class );
	
	static String FILE_PARA_MALFORMED = "File param is malformed";
	
	int LIVE = 10000;
	
	int ARENE_SIZE = 30;
	
	List<ModuleArena> mLstModuleArena = new ArrayList<>();
	
	private Arene mArene;
	
	String mValPercent="100";
	
	String mPathCost;
	
	int mPv = LIVE;
	
	int mSizeArene = ARENE_SIZE;
	
	ModuleArena mCurrentModuleArena = null;
	
	Robot mCurrentRobot =null;
	
	void parseAreneProps (String pPath) throws DawaException {
		initArene();
		
		try (In lIn = new In()){
			lIn.open(pPath);
			String lLigne = lIn.readLine();
			
		while (lLigne != null) {
				lLigne = lLigne.trim();
				parseLigne (lLigne);
				lLigne = lIn.readLine();//new line
			}//enwhile
		}//end try
		mCurrentRobot.reInit();
		
		initAreneSize();
		mArene.setLstCase(mLstModuleArena);
		LOGGER.info("Found {} robot(s)",mLstModuleArena.size());
		
	
	}
	
	
	void parseLigne (String pLigne) throws DawaException{
		if (pLigne.equals("[Robot]")) {
			
			if (mCurrentRobot != null) {
				mCurrentRobot.reInit();//renit with founded information
			}
			
			//next robot
			mCurrentModuleArena = new ModuleArena();
			mCurrentModuleArena.setIsRobot();
			mLstModuleArena.add(mCurrentModuleArena);
			Robot lRobot = new Robot();
		
			RobotsProps lRobotProps = new RobotsProps();
			lRobotProps.setName("?");
			lRobot.init(lRobotProps);
			
			
			
			lRobot.setInArena(true);
			lRobot.setEnergie(new Energie (mPv));
			mCurrentRobot = lRobot;
			
			mCurrentModuleArena.setObjetArene(lRobot);
		}
		else if (pLigne.startsWith("Arene.obstacle")) {
			mValPercent = getProperties(pLigne);
		}
		else if (pLigne.startsWith("Arene.energie")) {
			mPv = Integer.parseInt(getProperties(pLigne));
			LOGGER.debug("Energie {}",mPv);
		}
		else if (pLigne.startsWith("Arene.cost")) {
			mPathCost = getProperties(pLigne);
		}
		else if (pLigne.startsWith("Arene.size")) {
			mSizeArene = Integer.parseInt(getProperties(pLigne));
		}
		else if (pLigne.startsWith("Name:")) {
			verify(mCurrentRobot);
			mCurrentRobot.getRobotProps().setName(getProperties(pLigne));
		}
		else if (pLigne.startsWith("Color:")) {
			verify(mCurrentRobot);
			mCurrentRobot.getRobotProps().setColor(getProperties(pLigne));
		}
		else if (pLigne.startsWith("Prg:")) {
			verify(mCurrentModuleArena);
			mCurrentModuleArena.setNamePrg(getProperties(pLigne));
		}
		else if (pLigne.startsWith("Pos:")) {
			verify(mCurrentRobot);
			Position lPos = convertInPos (getProperties(pLigne));
			mCurrentRobot.setPosition(lPos);
			
		}
		else if (pLigne.startsWith("#")) {
			//na: skipcomment
		}
		else {
			LOGGER.info("Ligne {} not processed",pLigne);
		}
		
	}
	
	
	void verify (Object pObjet) throws DawaException{
		if (pObjet == null) throw new DawaException (FILE_PARA_MALFORMED);
	}
	
	void initArene () {
		ConsoleGraphique lConsole = new ConsoleGraphique();
		
	    mArene = new Arene(lConsole);
	    mArene.setLstCase(mLstModuleArena);
		
		AreneProps lAreneProps = new AreneProps();
		lAreneProps.setSize(ARENE_SIZE);
		lAreneProps.setTitle("Arene");
	
		
		mArene.setAreneProps(lAreneProps);
	}
	
	void initAreneSize () {
		mArene.getAreneProps().setSize(mSizeArene);
	}
	
	
	String getProperties (String pLigne) {
		int lPos = pLigne.indexOf(":");
		return pLigne.substring(lPos+1);
	}
	
	Position convertInPos (String pLigne) {
		String[] lVal = pLigne.split(",");
		int lX= Integer.parseInt(lVal[0]);
		int lY= Integer.parseInt(lVal[1]);
		
		return new Position(lX,lY);
		
	}
	
	public String toString () {
		return mLstModuleArena.toString();
	}
	
	public Arene getArene () {
		return mArene;
	}

}
