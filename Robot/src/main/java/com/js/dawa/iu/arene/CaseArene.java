package com.js.dawa.iu.arene;

import java.util.ArrayList;
import java.util.List;

import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.iu.arene.render.CaseAreneRender;
import com.js.dawa.robot.model.Attribut;

public class CaseArene {
	
	List<Attribut> mLstAttribut = new ArrayList<>();
	CaseAreneRender mCaseRender = new CaseAreneRenderDefaut();
	
	
	void addAttribut (Attribut pAttribut) {
		mLstAttribut.add(pAttribut);
	}
	
	CaseAreneRender getRender () {
		return mCaseRender;
	}
	
	public void setCaseAreneRender (CaseAreneRender pCaseAreneRender) {
		mCaseRender = pCaseAreneRender;
	}

}
