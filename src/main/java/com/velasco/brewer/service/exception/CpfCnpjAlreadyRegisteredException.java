package com.velasco.brewer.service.exception;

public class CpfCnpjAlreadyRegisteredException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public CpfCnpjAlreadyRegisteredException(String message) {
		super(message);
	}

}
