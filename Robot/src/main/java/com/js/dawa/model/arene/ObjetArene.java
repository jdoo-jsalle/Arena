package com.js.dawa.model.arene;

import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.model.robot.Attribut;
import com.js.dawa.model.robot.DataBoard;
import com.js.dawa.model.robot.Position;

public interface ObjetArene {
	
	
	public CaseRender getRender ();
	
	public Position getPosition ();
	public void setPosition (Position pPosition);
	
	public Map<String, Attribut>  getProps ();
	
	public void setInArena (boolean pIsInArena);
	
	public boolean isDispose ();
	
	public void add (int px, int py);
	
	public DataBoard getDataBoard();
	
	public boolean isVisible ();
	
	public void setVisible (boolean pVisible);
	
	public void setEnergie (Energie pEnergie);
	
	public Energie getEnergie ();

}
