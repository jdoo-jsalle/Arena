package com.js.dawa.prog;

import java.io.File;
import java.util.List;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.CreateDefaultCase;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.prog.instruction.CostInstruction;
import com.js.dawa.prog.parse.ParseLigneCmd;
import com.js.dawa.util.DawaException;
import com.js.dawa.util.In;

public class ParserDirParams {
	
	static String ARENE_PROPERTIES = "Arene.properties";
	
	ParserAreneProps mParserAreneProps;
	
	CostInstruction mCostInstruction = new CostInstruction();
	
	String mPath;
	
	Arene mArene;
	
	/**
	 * Reader dir params
	 *   seek : Arene.propeties
	 *   seek : Robot*
	 * @param pPath
	 * @throws DawaException
	 */
	public void parseDirParams (String pPath) throws DawaException{
		initCostInstruction();
		mPath = pPath;
	
		File lDir = new File (pPath);
		if (lDir.isDirectory()) {
			File lFile = new File (pPath  + ARENE_PROPERTIES);
			
			mParserAreneProps = new ParserAreneProps();
			mParserAreneProps.parseAreneProps(lFile.getAbsolutePath());
			mArene = mParserAreneProps.getArene();
			
			
			parsePrg();
			addObstacle(mParserAreneProps.mValPercent);
		
			
			
		}
		else {
			throw new DawaException("Dir " + pPath + " is not valid path directory");
		}
	}
	
	
	void addObstacle (String pValPercent) {
		CreateDefaultCase lCreate = new CreateDefaultCase();
		lCreate.createDefaultCase(mArene, mArene.getLstCaseMain(), Integer.parseInt(pValPercent));
	}

	
	void initCostInstruction () {
		mCostInstruction.addCost("affect", 0);
		mCostInstruction.addCost("block", 0);
		mCostInstruction.addCost("if", 1);
		mCostInstruction.addCost("avancer", 4);
		mCostInstruction.addCost("fake", 0);
		mCostInstruction.addCost("tir", 3);
		mCostInstruction.addCost("invisible", 20);
		mCostInstruction.addCost("mine", 10);
		mCostInstruction.addCost("fuite", 4);
		mCostInstruction.addCost("poursuite", 4);
		
		
	}
	
	void parsePrg () throws DawaException {
		for (ModuleArena lmodule : mParserAreneProps.mLstModuleArena) {
			ParseLigneCmd lParseLigneCmd = new ParseLigneCmd(lmodule.getObjetArene(), mArene);
			lParseLigneCmd.setCostInstruction(mCostInstruction);
			try (In lIn = new In()){
				lIn.open(mPath  + lmodule.getNamePrg()  );
				String lLigne = lIn.readLine();
				while (lLigne != null) {
					
					lParseLigneCmd.parse(lLigne);
					
					
					lLigne = lIn.readLine();
				}
				
				
				lmodule.setInstructionInit(lParseLigneCmd.getInit());
				lmodule.setInstructionLoop(lParseLigneCmd.getMain());
				
			}
			
			
		}
		
	}
	
	public Arene getArene () {
		return mArene;
	}
	
	public List<ModuleArena> getLstCase (){
		return mParserAreneProps.mLstModuleArena;
	}
}
