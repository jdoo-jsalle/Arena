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
	
	ParserAreneProps mParserAreneProps;
	
	String mPath;
	
	Arene mArene;
	
	CostInstruction mCostInstruction;
	
	/**
	 * Reader dir params
	 *   seek : Arene.propeties
	 *   seek : Robot*
	 * @param pPath
	 * @throws DawaException
	 */
	public void parseDirParams (String pPath, String pFileArena) throws DawaException{
		
		mPath = pPath;
	
		File lDir = new File (pPath);
		if (lDir.isDirectory()) {
			File lFile = new File (pPath  + pFileArena);
			
			mParserAreneProps = new ParserAreneProps();
			mParserAreneProps.parseAreneProps(lFile.getAbsolutePath());
			//seek Cost instruction
			mArene = mParserAreneProps.getArene();
			
			ParserCostInstruction lParserCost = new ParserCostInstruction(mPath);
			lParserCost.loadCost( mParserAreneProps.mPathCost);
			mCostInstruction =lParserCost.mCostInstruction;
			
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
