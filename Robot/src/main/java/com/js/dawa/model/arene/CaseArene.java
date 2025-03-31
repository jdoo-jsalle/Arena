package com.js.dawa.model.arene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.model.robot.Attribut;
import com.js.dawa.model.robot.DataBoard;
import com.js.dawa.model.robot.Position;

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

	@Override
	public Map<String, Attribut> getProps() {
		//nothing
		return new HashMap<>();
	}

	@Override
	public void setInArena(boolean pIsInArena) {
		//na
		
	}

	@Override
	public boolean isDispose() {
		return false;
	}

	@Override
	public void add(int px, int py) {
		//objet immobile
		
	}

	@Override
	public DataBoard getDataBoard() {
		return null;
	}

	@Override
	public boolean isVisible() {
		//objet invisible
		return false;
	}

	@Override
	public void setVisible(boolean pVisible) {
		//na
		
	}

}
