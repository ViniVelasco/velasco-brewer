package com.velasco.brewer.service.exception;

public class PasswordRequiredUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PasswordRequiredUserException(String message) {
		super(message);
	}

}
