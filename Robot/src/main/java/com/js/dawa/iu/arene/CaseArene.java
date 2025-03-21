package com.js.dawa.iu.arene;

import java.util.ArrayList;
import java.util.List;

import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.robot.model.Attribut;
import com.js.dawa.robot.model.Position;

public class CaseArene implements ObjetArene{
	
	List<Attribut> mLstAttribut = new ArrayList<>();
	CaseRender mCaseRender = new CaseAreneRenderDefaut();
	Position mPosition;
	
	void addAttribut (Attribut pAttribut) {
		mLstAttribut.add(pAttribut);
	}
	
	public CaseRender getRender () {
		return mCaseRender;
	}
	
	public void setPosition (Position pPosition) {
		mPosition = pPosition;
	}
	
	public void setCaseAreneRender (CaseRender pCaseAreneRender) {
		mCaseRender = pCaseAreneRender;
	}
	
	public Position getPosition () {
		return mPosition;
	}

}
