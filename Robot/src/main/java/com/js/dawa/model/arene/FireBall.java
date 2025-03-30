package com.js.dawa.model.arene;

import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.FireBallRender;
import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.model.robot.Attribut;
import com.js.dawa.model.robot.DataBoard;
import com.js.dawa.model.robot.Position;

public class FireBall implements ObjetArene {
	
	Position mPosition;
	FireBallRender mRender;
	boolean mIsDispose = false;
	private String mColor ="blue";


	@Override
	public CaseRender getRender() {
		if (mRender == null) {
			InfoRender lInforInfoRender = new InfoRender();
			lInforInfoRender.setColor(mColor);
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
	
	public void setColor (String pColor) {
		mColor = pColor;
	}

	@Override
	public boolean isVisible() {
		return false;
	}

	@Override
	public void setVisible(boolean pVisible) {
		//na
		
	}

}
