package com.js.dawa.model.position;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.iu.arene.render.GridPatternSquare;

public class LaunchWayOfPosition {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( WayOfPosition.class );
	
	
	public static void main(String[] args) {
		WayOfPosition lWay = new WayOfPosition();
		
		
		Position lPos1 = new Position(10, 10);
		Position lPos2 = new Position (11,11);
		lWay.addPosition(lPos1);
		lWay.addPosition(lPos2);
		
		GridPatternSquare lGrid = new GridPatternSquare();
		
		LOGGER.debug("Pos {} {}",lWay.computeNext(40),lGrid.transform(lWay.computeNext(40)));
		LOGGER.debug("Pos {} {}",lWay.computeNext(80),lGrid.transform(lWay.computeNext(80)));
		LOGGER.debug("Pos {} {}",lWay.computeNext(120),lGrid.transform(lWay.computeNext(120)));
		LOGGER.debug("Pos {} {}",lWay.computeNext(160),lGrid.transform(lWay.computeNext(160)));
		LOGGER.debug("Pos {} {}",lWay.computeNext(200),lGrid.transform(lWay.computeNext(200)));
		LOGGER.debug("Pos {} {}",lWay.computeNext(240),lGrid.transform(lWay.computeNext(240)));
	}

}
