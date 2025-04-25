package com.js.dawa.model.position;

import com.js.dawa.model.arene.ObjetArene;

public interface WayOfPosition {
	
	default void init (ObjetArene pObjetArene) {
		//na
	}
	
	public void addPosition (Position pPosition);
	
	public Position computeNext ();

}
