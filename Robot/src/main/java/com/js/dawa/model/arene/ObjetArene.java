package com.js.dawa.model.arene;

import java.util.List;
import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.model.position.Position;
import com.js.dawa.model.robot.Attribut;
import com.js.dawa.model.robot.DataBoard;

public interface ObjetArene {
	
	
	public List<CaseRender> getRender ();
	
	public Position getPosition ();
	
	default Position getPositionScreen() {
		return getPosition();
	}

	public void setPosition (Position pPosition);
	
	public void addAttribut (Attribut pAttribut);
	
	public Map<String, Attribut>  getProps ();
	
	public void setInArena (boolean pIsInArena);
	
	public boolean isDispose ();
	
	public void add (double px, double py);
	
	public DataBoard getDataBoard();
	
	default boolean isVisible () {
		return true;
	}
	
	default void setVisible (boolean pVisible) {
		//na
	}
	
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
	
	default boolean isPrgBlock () {
		return false;
	}
	
	default void setPrgBlock(boolean pPrgBlock) {
		//na
	}
	
	default void setOver () {
		//na
	}
	
	default String getName() {
		return "";
	}

}
