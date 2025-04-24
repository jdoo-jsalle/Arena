package com.js.dawa.model.arene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.js.dawa.iu.arene.render.CaseAreneRenderDefaut;
import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.model.position.Position;
import com.js.dawa.model.robot.Attribut;
import com.js.dawa.model.robot.DataBoard;

public class CaseArene implements ObjetArene{
	
	HurtObject mHurtObject = new HurtObject(0);//20 default value, change it in add properties HIT
	
	Map<String, Attribut> mLstAttribut = new HashMap <>();
	
	List<CaseRender> mCaseRender = new ArrayList<>();
	
	Position mPosition;
	
	boolean mIsVisible = true;
	
	
	public void addAttribut (Attribut pAttribut) {
		if (pAttribut.getNameAttribut().equals(HurtObject.HIT)) {
			mHurtObject.setHit(pAttribut.getValueAttribut());
		}
		else {
			mLstAttribut.put(pAttribut.getNameAttribut(), pAttribut);
		}
		
	}
	
	public List<CaseRender> getRender () {
		if (mCaseRender.isEmpty()) {
			mCaseRender.add(new CaseAreneRenderDefaut());
		}
		return mCaseRender;
	}
	
	public void setPosition (Position pPosition) {
		mPosition = pPosition;
	}
	
	public void addCaseAreneRender (CaseRender pCaseAreneRender) {
		mCaseRender.add(pCaseAreneRender);
	}
	
	public Position getPosition () {
		return mPosition;
	}

	@Override
	public Map<String, Attribut> getProps() {
		return mLstAttribut;
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
	public void add(double px, double py) {
		//objet immobile
		
	}

	@Override
	public DataBoard getDataBoard() {
		return null;
	}

	@Override
	public boolean isVisible() {
		return mIsVisible;
	}

	@Override
	public void setVisible(boolean pVisible) {
		mIsVisible = pVisible;
		
	}

	@Override
	public void setEnergie(Energie pEnergie) {
		//na : set something like energy tank ?
		
	}

	@Override
	public Energie getEnergie() {
		//na
		return null;
	}

	@Override
	public String getColor() {
		return "black";
	}

	@Override
	public boolean  collision(ObjetArene pObjeArene) {
		mHurtObject.init(this);
		mHurtObject.collision(pObjeArene);
		return true;//keep objet after collision
		
	}
	
	public String toString() {
		return "CaseArene " + mPosition.toString() + " Visible : " + Boolean.toString(mIsVisible);
	}

}
