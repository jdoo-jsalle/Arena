package com.js.dawa.prog.instruction;

import com.js.dawa.model.arene.Arene;
import com.js.dawa.model.arene.Mine;
import com.js.dawa.model.arene.ModuleArena;
import com.js.dawa.model.arene.ObjetArene;
import com.js.dawa.model.robot.Position;
import com.js.dawa.util.DawaException;

public class InsMine implements Instruction {
	
	ObjetArene mObjetArene;
	Arene mArene;
	Args mArgs;

	@Override
	public void init(Args pArgsInstruction, ObjetArene pObjetArena, Arene pArene) throws DawaException {
		mArene = pArene;
		mArgs = pArgsInstruction;
		mObjetArene = pObjetArena;

	}

	@Override
	public InfoExecIns execInstruction() throws DawaException {
		Position lPosObjetArena = mObjetArene.getPosition();
		//compute position from ObjetAren
		Position lPosition = new Position(lPosObjetArena.getX(), lPosObjetArena.getY());
		
		
		Mine lMine = new Mine();
		lMine.setPosition(lPosition);
		lMine.setColor(mObjetArene.getColor());
		lMine.setColor(mObjetArene.getColor());
		lMine.setOwner(mObjetArene);
		
			
		ModuleArena lModuleMine = new ModuleArena();
		lModuleMine.setObjetArene(lMine);
			
		mArene.addObjetArene(lModuleMine);
		
		return  new InfoExecIns(this);
	}

	@Override
	public void setFlag(String pVal) {
	    //na

	}

	@Override
	public String dump(String pDecal) {
		
		return "Mine";
	}

	@Override
	public Args getArgs() {
		
		return mArgs;
	}

}
