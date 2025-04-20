package com.js.dawa.model.arene;

import java.util.List;
import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.model.robot.Attribut;
import com.js.dawa.model.robot.DataBoard;
import com.js.dawa.model.robot.Position;

public interface ObjetArene {
	
	
	public List<CaseRender> getRender ();
	
	public Position getPosition ();
	
	public void setPosition (Position pPosition);
	
	public void addAttribut (Attribut pAttribut);
	
	public Map<String, Attribut>  getProps ();
	
	public void setInArena (boolean pIsInArena);
	
	public boolean isDispose ();
	
	public void add (int px, int py);
	
	public DataBoard getDataBoard();
	
	default boolean isVisible () {
		return true;
	}
	
	public void setVisible (boolean pVisible);
	
	public void setEnergie (Energie pEnergie);
	
	public Energie getEnergie ();
	
	public String getColor ();
	
	public boolean collision (ObjetArene pObjeArene);//return if block or not
	
	default ObjetArene getOwner() {
		  return this;
	}
	
	default void reInit () {
	 //na
	}
	
	
	default ModuleArena getModule() {
		return null;
	}

}
