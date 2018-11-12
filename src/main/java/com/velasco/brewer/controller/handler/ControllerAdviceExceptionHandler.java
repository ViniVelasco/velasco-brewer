package com.velasco.brewer.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.velasco.brewer.service.exception.NameStyleAlreadyRegisteredException;

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

	@ExceptionHandler(NameStyleAlreadyRegisteredException.class)
	public ResponseEntity<String> NameStyleAlreadyRegisteredException(NameStyleAlreadyRegisteredException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
}
