package com.js.dawa.util;

public class DawaRunTimeException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DawaRunTimeException (String pMessage) {
		super(pMessage);
	}
	
	public DawaRunTimeException (String pMessage,Throwable pe) {
		super(pMessage,pe);
	}
	

}
