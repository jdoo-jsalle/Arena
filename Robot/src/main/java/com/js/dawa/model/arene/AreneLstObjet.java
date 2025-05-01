package com.js.dawa.model.arene;

import java.util.ArrayList;
import java.util.List;

public class AreneLstObjet {
	
	private List<ModuleArena> mLstModuleArene = new ArrayList<>();
	
	
	List <ModuleArena> getLstCaseMain (){
		return mLstModuleArene;
	}
	
	
	public void setLstCase (List<ModuleArena> pLstModuleArena) {
		mLstModuleArene = pLstModuleArena;
	}
	

}
