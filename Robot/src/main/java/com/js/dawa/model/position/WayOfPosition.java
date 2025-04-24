package com.js.dawa.model.position;

import java.util.ArrayList;
import java.util.List;

public class WayOfPosition {
	
	
	List<Position> mLstPos  = new ArrayList<>();
	
	int mtotPixBySeconde = 15;
	
	long mStart;
	
	
	public void addPosition (Position pPosition) {
		if (mLstPos.isEmpty()) {
			mStart = System.currentTimeMillis();
		}
		mLstPos.add(pPosition);
		
	}
	
	Position computeNext () {
		
		long mNow = System.currentTimeMillis();
		long lTime = mNow - mStart;
		long lDistance = mtotPixBySeconde * lTime /1000;
		mStart = mNow;//refresh time
		
		return compute (lDistance);
		
	}
	
	Position compute (double pDistance) {
		
		Position lPos = null;
		
		//get the two firt point
		if (mLstPos.size() > 1) {
			Position lFirst = mLstPos.get(0);
			Position lFormer = mLstPos.get(1);
			double lDis = lFirst.distance(lFormer);
			
			//compute tot Pixel accross during last time tag
		
			if (pDistance > lDis) {
				//consomer la première position
				mLstPos.remove(0);
				lPos = compute (pDistance-lDis);
				
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
				double x0 =-(pDistance * (lPos2.getX()-lPos1.getX())/lDis)/lPos2.getX();
				double y0 =-(pDistance * (lPos2.getY()-lPos1.getY())/lDis)/lPos2.getY();
				//new position for Pos1
				lPos1.setXY(x0, y0);
				lPos = lPos1;
			}
			
		}
		else {
			if (mLstPos.size() == 1) {
				lPos = mLstPos.get(0);
			}
		}
		
		return lPos;
	}

}
