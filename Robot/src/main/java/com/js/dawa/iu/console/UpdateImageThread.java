package com.js.dawa.iu.console;

import javax.swing.JFrame;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UpdateImageThread implements Runnable{
	
	private static final Logger LOGGER =  LoggerFactory.getLogger( UpdateImageThread.class );
	
	JFrame mFrame;
	
	boolean mEnd= false;
	
	Thread lThread;
	
	UpdateImageThread (JFrame pLabel){
		mFrame = pLabel;
	}
	
	void start (){
		LOGGER.debug("run thread");
		lThread = new Thread (this);
		lThread.start();
	}
	


	@Override
	public void run() {
		while (!mEnd) {
			LOGGER.debug("loop repaint");
			mFrame.repaint();
			try {
				Thread.sleep(41);
			}
			catch (InterruptedException le) {
				LOGGER.debug("error",le);
				Thread.currentThread().interrupt();
			}
		}
		
	}
	
	public void setEnd () {
		mEnd = true;
	}

}
