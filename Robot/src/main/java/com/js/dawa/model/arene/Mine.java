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

public class Mine implements ObjetArene {

	HurtObject mHurtObject = new HurtObject(200);//200 default value, change it in add properties HIT
	
	Position mPosition;
	List<CaseRender> mRender;
	boolean mIsDispose = false;
	private String mColor ="black";
	Energie mEnergie = new Energie(0);
	Map<String, Attribut> mLstAttribut = new HashMap <>();
	ObjetArene mOwner;//owner of the mine
	
	@Override
	public List<CaseRender> getRender() {
		if (mRender == null) {
			mRender = new ArrayList<>();
			InfoRender lInforInfoRender = new InfoRender();
			lInforInfoRender.setColor(mColor);
			lInforInfoRender.setString("+");
			lInforInfoRender.setFont("BOLD");
			lInforInfoRender.setSizePolice(15);
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
		if (pAttribut.getNameAttribut().equals(HurtObject.HIT)) {
			mHurtObject.setHit(pAttribut.getValueAttribut());
		}
		else {
			mLstAttribut.put(pAttribut.getNameAttribut(), pAttribut);
		}

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
		return mIsDispose;
	}

	@Override
	public void add(int px, int py) {
		//na : mine is immobile

	}

	@Override
	public DataBoard getDataBoard() {
		//no DataBorad
		return null;
	}

	@Override
	public boolean isVisible() {
		
		return true;
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
		//na
		return null;
	}

	@Override
	public String getColor() {
		
		return mColor;
	}
	
	public void setOwner (ObjetArene pObjeArene) {
		mOwner = pObjeArene;
	}

	@Override
	public boolean  collision(ObjetArene pObjeArene) {
		if (pObjeArene.getOwner() != mOwner) {
			mHurtObject.init(this);
			mHurtObject.collision(pObjeArene);
			
			HurtObjetRender lObjetHurt = new HurtObjetRender();
			pObjeArene.getRender().add(lObjetHurt);
			mIsDispose= true;
			ModuleArena lModuleArena = pObjeArene.getModule();
			if (lModuleArena != null) {
				lModuleArena.replaceInstruction();//replace instruction courante
			}
		}
		
		
		
		return false;
	}
	
	@Override
	public ObjetArene getOwner () {
		return mOwner;
	}
	
	public void setColor (String pColor) {
		mColor = pColor;
	}

}
