package com.js.dawa.model.arene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.FireBallRender;
import com.js.dawa.iu.arene.render.InfoRender;
import com.js.dawa.iu.arene.render.HurtObjetRender;
import com.js.dawa.model.robot.Attribut;
import com.js.dawa.model.robot.DataBoard;
import com.js.dawa.model.robot.Position;

public class FireBall implements ObjetArene {
	
	
	HurtObject mHurtObject = new HurtObject(20);//20 default value, change it in add properties HIT
	
	Position mPosition;
	List <CaseRender> mRender;
	boolean mIsDispose = false;
	private String mColor ="blue";
	Energie mEnergie = new Energie(Integer.MAX_VALUE);
	Map<String, Attribut> mLstAttribut = new HashMap <>();

	ObjetArene mOwner;
	
	public void addAttribut (Attribut pAttribut) {
		
		if (pAttribut.getNameAttribut().equals(HurtObject.HIT)) {
			mHurtObject.setHit(pAttribut.getValueAttribut());
		}
		else {
			mLstAttribut.put(pAttribut.getNameAttribut(), pAttribut);
		}
		
	}

	@Override
	public List<CaseRender> getRender() {
		if (mRender == null) {
			mRender = new ArrayList<>();
			InfoRender lInforInfoRender = new InfoRender();
			lInforInfoRender.setColor(mColor);
			lInforInfoRender.setString("*");
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
	
	public void setOwner (ObjetArene pObjeArene) {
		mOwner = pObjeArene;
	}

	@Override
	public void setPosition(Position pPosition) {
		mPosition = pPosition;

	}

	@Override
	public Map<String, Attribut> getProps() {
		return 	mLstAttribut;
	
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
		mPosition.addXY(px, py);
		
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

	@Override
	public void setEnergie(Energie pEnergie) {
		//na
		
	}

	@Override
	public Energie getEnergie() {
		return mEnergie;
	}

	@Override
	public String getColor() {
		
		return mColor;
	}

	@Override
	public boolean  collision(ObjetArene pObjeArene) {
		if (mOwner != pObjeArene.getOwner()) {//not hurt by his own fireball
			mHurtObject.init(this);
			mHurtObject.collision(pObjeArene);
			HurtObjetRender lObjetHurt = new HurtObjetRender();
			pObjeArene.getRender().add(lObjetHurt);
			mIsDispose = true;//remove this object
		}
		
		
		return false;
		
	}
	
	@Override
	public ObjetArene getOwner () {
		return mOwner;
	}

}
