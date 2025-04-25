package com.js.dawa.model.arene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.FireBallRender;
import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.model.position.Position;
import com.js.dawa.model.robot.Attribut;
import com.js.dawa.model.robot.DataBoard;

public class Explosion implements ObjetArene {
	
	long mStart = -1;
	
	List<CaseRender> mRender;
	
	Position mPosition;

	@Override
	public List<CaseRender> getRender() {
		if (mRender == null) {
			mRender = new ArrayList<>();
			InfoRender lInforInfoRender = new InfoRender();
			lInforInfoRender.setColor("orange");
			lInforInfoRender.setString("^");
			CaseRender lRender = new FireBallRender();
			lRender.setInfoRender(lInforInfoRender);
			mRender.add(lRender);
			
		}
		
		return mRender;
	}

	@Override
	public Position getPosition() {
		
		return mPosition;
	}

	@Override
	public void setPosition(Position pPosition) {
		mPosition = pPosition;

	}

	@Override
	public void addAttribut(Attribut pAttribut) {
		//na

	}

	@Override
	public Map<String, Attribut> getProps() {
		
		return new HashMap<>();
	}

	@Override
	public void setInArena(boolean pIsInArena) {
		//na

	}

	@Override
	public boolean isDispose() {
		if (mStart == -1) {
			mStart = System.currentTimeMillis();
		}
		
		return  System.currentTimeMillis() - mStart > 500;
	}

	@Override
	public void add(double px, double py) {
		//immobile

	}

	@Override
	public DataBoard getDataBoard() {
		return null;
	}

	@Override
	public void setEnergie(Energie pEnergie) {
		//no Energie

	}

	@Override
	public Energie getEnergie() {
		return null;
	}

	@Override
	public String getColor() {
		return "red";
	}

	@Override
	public boolean collision(ObjetArene pObjeArene) {
		//no collision
		return false;
	}

}
