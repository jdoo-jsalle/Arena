package com.js.dawa.prog.instruction;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.FireBall;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.robot.Position;
import com.js.dawa.util.DawaException;

public class InsTir implements Instruction {
	
	ObjetArene mObjetArene;
	Arene mArene;
	int mX =0;
	int mY =0;
	Args mArgs;

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArena, Arene pArene) throws DawaException {
		mObjetArene = pObjetArena;
		mArene = pArene;
		mArgs = pArgsInstruction;
	
		

	}

	@Override
	public InfoExecIns execInstruction() throws DawaException {
		verify();
		if (mX != 0 ||  mY != 0) {
			Position lPosObjetArena = mObjetArene.getPosition();
			//compute position from ObjetAren
			Position lPosition = new Position(lPosObjetArena.getX()+ mX, lPosObjetArena.getY()+ mY);
			
			
			FireBall lFireBall = new FireBall();
			lFireBall.setPosition(lPosition);
			lFireBall.setColor(mObjetArene.getColor());
			
			InsAvancer lAvancer = new InsAvancer();
			Args lArgs = new Args(lFireBall);
			lArgs.addArguments(Integer.toString(mX));
			lArgs.addArguments(Integer.toString(mY));
			lAvancer.init(lArgs, lFireBall, mArene);
			
			ModuleArena lModuleFireBall = new ModuleArena();
			lModuleFireBall.setObjetArene(lFireBall);
			lModuleFireBall.setInstructionLoop(lAvancer);
			
			mArene.addObjetArene(lModuleFireBall);
		}
		//else => pas de vitesse, la fire ball ne part pas
		return new InfoExecIns(this);

	}

	@Override
	public void setFlag(String pVal) {
		//na

	}
	
	void verify () throws DawaException {
		//1:int (x) 2:int (y)
		mX = mArgs.getArgsInt(0);
		mY = mArgs.getArgsInt(1);
		
	}

	@Override
	public String dump(String pDecal) {
		
		return pDecal + toString();
	}
	
	public String toString() {
		return "Tir " + mArgs.toString();
	}

	@Override
	public Args getArgs() {
		return mArgs;
	}

}
