package com.sanbhat.aptmgr.exceptions;

public class UserException extends Exception {

	private static final long serialVersionUID = 4411463569227661799L;

	public UserException(String message) {
		super(message);
	}
	
	public UserException(Throwable t) {
		super(t);
	}
	
	public UserException(String message, Throwable t) {
		super(message, t);
	}
}
