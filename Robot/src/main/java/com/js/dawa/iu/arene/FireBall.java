package com.js.dawa.iu.arene;

import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.FireBallRender;
import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.robot.model.Attribut;
import com.js.dawa.robot.model.Position;

public class FireBall implements ObjetArene {
	
	Position mPosition;
	FireBallRender mRender;

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

}
