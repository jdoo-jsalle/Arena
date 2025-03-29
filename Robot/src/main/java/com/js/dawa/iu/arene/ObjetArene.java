package com.js.dawa.iu.arene;

import java.util.Map;

import com.js.dawa.iu.arene.render.CaseRender;
import com.js.dawa.robot.model.Attribut;
import com.js.dawa.robot.model.DataBoard;
import com.js.dawa.robot.model.Position;

public interface ObjetArene {
	
	
	public CaseRender getRender ();
	
	public Position getPosition ();
	public void setPosition (Position pPosition);
	
	public Map<String, Attribut>  getProps ();
	
	public void setInArena (boolean pIsInArena);
	
	public boolean isDispose ();
	
	public void add (int px, int py);
	
	public DataBoard getDataBoard();
	

}
