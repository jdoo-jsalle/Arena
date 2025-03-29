package com.js.dawa.iu.arene;

import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.FireBallRender;
import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.robot.model.Attribut;
import com.js.dawa.robot.model.DataBoard;
import com.js.dawa.robot.model.Position;

public class FireBall implements ObjetArene {
	
	Position mPosition;
	FireBallRender mRender;
	boolean mIsDispose = false;

	@Override
	public CaseRender getRender() {
		if (mRender == null) {
			InfoRender lInforInfoRender = new InfoRender();
			lInforInfoRender.setColor("cyan");
			lInforInfoRender.setString("*");
			mRender = new FireBallRender();
			mRender.setInfoRender(lInforInfoRender);
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
	public Map<String, Attribut> getProps() {
		//na
		return null;
	}

	@Override
	public void setInArena(boolean pIsInArena) {
		if (!pIsInArena) {//sort de l'arene => dispose objet
			mIsDispose = true;
		}
		
	}

	@Override
	public boolean isDispose() {
		return mIsDispose;
		
	}

	@Override
	public void add(int px, int py) {
		mPosition.addXY(px, py);;
		
	}

	@Override
	public DataBoard getDataBoard() {
		//no databoard for firewall
		return null;
	}

}
