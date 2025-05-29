package com.js.dawa.model.position;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.js.dawa.model.arene.ObjetArene;


public class WayOfPositionSquare implements WayOfPosition{
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( WayOfPositionSquare.class );
	
	List<Position> mLstPos  = new ArrayList<>(2);
	
	ObjetArene mObjetArene;
		
	long mStart;
	
	
	
	@Override
	public void init(ObjetArene pObjetArena) {
		mObjetArene = pObjetArena;
	}
	
	
	public void addPosition (Position pPosition) {
		mStart = System.currentTimeMillis();
		mLstPos.add(pPosition);
	}
	
	public Position computeNext () {
		
		long lNow = System.currentTimeMillis();
		long lDuration = lNow - mStart;
		LOGGER.debug("durations is {}",lDuration);
		return computeNext (lDuration);
		
	}
	
	

	Position computeNext (double pDuration) {
		double lDistance = getDistanceForTheDuration(pDuration);
		return compute(lDistance);
	}
	
	Position compute (double pDistanceRapport) {
		LOGGER.debug("rapport {}",pDistanceRapport);
		Position lPos = null;
		
		//get the two firt point
		if (mLstPos.size() > 1) {
			setPrgBlock(true);
			Position lFirst = mLstPos.get(0);
			Position lFormer = mLstPos.get(1);
			double lDisTotal = lFirst.distance(lFormer);
			double lDistance = lDisTotal*pDistanceRapport;
			
			LOGGER.debug("dis parc {} ; dis total {}",lDistance,lDisTotal);
			
			//compute tot Pixel accross during last time tag
		
			if (lDistance >= lDisTotal) {
				setPrgBlock(false);
				//consomer la première position
				mLstPos.remove(0);
				return mLstPos.get(0);
				
			}
			else {
				Position lPos1 = mLstPos.get(0);
				Position lPos2 = mLstPos.get(1);
				//compute new position of x1,y1, for accrossed distance
				//cos(a) = (x2-x1)/hyp
				//sin(a) = (y2-x1)/hyp.
				//Distance beetween the thwo point is hypothenus.
				//Dis accrossed is the new hypothnus, on the Distance/
				//compute x0 and y0 the point where is after dis accrossed
				double x0 =(lDistance * (lPos2.getX()-lPos1.getX())/lDisTotal)+lPos1.getX() ;
				double y0 =(lDistance * (lPos2.getY()-lPos1.getY())/lDisTotal)+lPos1.getY() ;
				LOGGER.debug("x0 :{} y0 : {}",x0,y0);
				//new position for Pos1
				
				lPos = new Position (x0,y0,lFirst.getAxe());//new intermediary position
		
			}
			
		}
		else {
			if (mLstPos.size() == 1) {
				lPos = mLstPos.get(0);
				setPrgBlock(false);
			}
		}
		
		return lPos;
	}
	
	void setPrgBlock (boolean pBlock) {
		if (mObjetArene!= null)
			mObjetArene.setPrgBlock(pBlock);
			
	}
	

	double getDistanceForTheDuration (double pDuration) {
		return  pDuration  /1000;//milliseconde
	}
	
	
	public int getTotPos() {
		return mLstPos.size();
	}

}
