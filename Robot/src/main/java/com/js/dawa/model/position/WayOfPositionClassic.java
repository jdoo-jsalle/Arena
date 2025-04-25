package com.js.dawa.model.position;

public class WayOfPositionClassic implements WayOfPosition {
	
	Position mPosition;
	
	

	@Override
	public void addPosition(Position pPosition) {
		mPosition = pPosition;

	}

	@Override
	public Position computeNext() {
		
		return mPosition;
	}

}
