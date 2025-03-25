package com.js.dawa.util;

public class DawaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public DawaException (String pMsg) {
		super (pMsg);
	}
	
	public DawaException (String pMsg, Throwable pe) {
		super (pMsg,pe);
	}
	
	public DawaException () {
		super ();
	}

}
