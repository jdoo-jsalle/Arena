package com.js.dawa.model.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.model.arene.Energie;
import com.js.dawa.model.arene.ObjetArene;

public class Robot implements ObjetArene{
	
	Position mPosition;
	
	RobotsProps mRobotProps;
	
	List<CaseRender>  mRobotRender  = new ArrayList<>();
	
	private DataBoard mRobotData = new DataBoard();//store information for Robot
	
	Energie mEnergie = new Energie();
	
	RobotRender mMainRender;
	

	
	public void init (RobotsProps pRobotProps) {
		mRobotProps = pRobotProps;
		if (mRobotProps != null) {
			mMainRender = new RobotRender(mRobotProps);
			mRobotRender.add(mMainRender);
			
		}
		reInit();
		
	}
	
	@Override
	public void reInit () {
		if (mMainRender != null) {
			mMainRender.reinit();
		}
		setVisible(true);
	}
	

	
	public void setRobotProps (RobotsProps pRobotProps) {
		mRobotProps = pRobotProps;
	}
	

	
	public List<CaseRender> getRender () {
		return mRobotRender;
	}
	
	
	public void setPosition (Position pPosition) {
		mPosition = pPosition;
	}

	@Override
	public Position getPosition() {
			return mPosition;
	}

	@Override
	public Map<String, Attribut> getProps() {
		return mRobotProps.getLstAttribut();
	}
	
	public RobotsProps getRobotProps () {
		return mRobotProps;
	}
	
	public void setBlocked() {
		mMainRender.setBlocked();
		mRobotData.setBlocked(true);
		
		
	}
	
	public void setDeBlocked() {
		mMainRender.reinit();
		mRobotData.setBlocked(false);
	}
	
	

	
	public void add (int pX, int pY) {
		mPosition.addX(pX);
		mPosition.addY(pY);
		if (pX  != 0 || pY != 0) //robot move => become visible
		    setVisible(true);
	}

	public DataBoard getRobotData() {
		return mRobotData;
	}

	public void setRobotData(DataBoard pRobotData) {
		this.mRobotData = pRobotData;
	}

	@Override
	public void setInArena(boolean pIsInArena) {
		if (!pIsInArena)
			setBlocked();
		else 
			setDeBlocked();
		
	}

	@Override
	public boolean isDispose() {
		return false;//Robot never dispose
	}

	@Override
	public DataBoard getDataBoard() {
		
		return getRobotData();
	}

	@Override
	public boolean isVisible() {
		return  mRobotProps != null && mRobotProps.isVisibilite();
	}

	@Override
	public void setVisible(boolean pVisible) {
		mRobotProps.setVisibilte(pVisible);
		if (!pVisible) {
			mMainRender.setHide();
		}
		else {
			mMainRender.reinit();
		}
		
	}

	@Override
	public void setEnergie(Energie pEnergie) {
		mEnergie = pEnergie;
		
	}

	@Override
	public Energie getEnergie() {
		return mEnergie;
	}

	@Override
	public String getColor() {
		
		return mRobotProps.getColor();
	}

	@Override
	public void addAttribut(Attribut pAttribut) {
		//na
		
	}

	@Override
	public boolean collision(ObjetArene pObjeArene) {
		setVisible(true);//collision => make it visible
		return true;
	}
	
	
	public String toString() {
		return "Robot : " + mPosition + " props : " + mRobotProps.toString() + " is visible : " + isVisible();
	}
	
	
	
	
}
