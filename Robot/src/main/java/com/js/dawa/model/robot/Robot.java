package com.js.dawa.model.robot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.model.arene.Energie;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.position.Position;
import com.js.dawa.model.position.WayOfPosition;

public class Robot implements ObjetArene{
	
	Position mPosition;
	
	RobotsProps mRobotProps;
	
	List<CaseRender>  mRobotRender  = new ArrayList<>();
	
	private DataBoard mRobotData = new DataBoard();//store information for Robot
	
	Energie mEnergie = new Energie();
	
	RobotRender mMainRender;
	
	WayOfPosition mWayOfPosition = new WayOfPosition();
	

	
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
		mWayOfPosition.addPosition(mPosition.clonePosition());
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
	
	

	
	public void add (double pX, double pY) {
		mPosition.addXY(pX,pY);
		mWayOfPosition.addPosition(mPosition.clonePosition());
		
		if (pX  != 0 || pY != 0) //robot move => become visible
		    setVisible(true);
	}
	
	@Override
	public Position getPositionScreen() {
		return mWayOfPosition.computeNext();
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
	
	@Override
	public String toString() {
		String lRobotProps= mRobotProps != null ? mRobotProps.toString() :" [robot props is null]";
		return "Robot : " + mPosition + " props : " + lRobotProps + " is visible : " + isVisible();
	}
	
	@Override
	public ModuleArena getModule() {
		return  mRobotProps != null ? mRobotProps.getModule() :null;
	}
	
	
	
	
}
