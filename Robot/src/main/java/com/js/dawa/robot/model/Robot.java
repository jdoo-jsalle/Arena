package com.js.dawa.robot.model;

import java.util.Map;

import com.js.dawa.iu.arene.ObjetArene;
import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.iu.arene.render.RobotRender;

public class Robot implements ObjetArene{
	
	Position mPosition;
	
	RobotsProps mRobotProps;
	
	RobotRender mRobotRender;
	
	private DataBoard mRobotData = new DataBoard();//store information for Robot

	

	
	public void init (RobotsProps pRobotProps) {
		mRobotProps = pRobotProps;
		mRobotRender = new RobotRender(pRobotProps);
	}
	
	public CaseRender getRender () {
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
	
	public void setBlocked() {
		mRobotRender.setColor("DARK_GRAY");
		mRobotData.setBlocked(true);
		
		
	}
	
	public void setDeBlocked() {
		mRobotRender.setColor(mRobotProps.getColor());
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
		return mRobotProps.isVisibilite();
	}

	@Override
	public void setVisible(boolean pVisible) {
		mRobotProps.setVisibilte(pVisible);
		if (!pVisible) {
			mRobotRender.setColor("white");//hide it
		}
		else {
			mRobotRender.setColor(mRobotProps.getColor());
		}
		
	}
	
	
	
}
