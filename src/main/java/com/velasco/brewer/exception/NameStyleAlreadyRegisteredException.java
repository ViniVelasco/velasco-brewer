package com.velasco.brewer.exception;

public class NameStyleAlreadyRegisteredException extends RuntimeException {
	
	private static final long serialVersionUID = 1l;
	
	public NameStyleAlreadyRegisteredException(String message) {
		super(message);
	}

}
