package com.GMH.digital.BarberPub.by.GMH.exception;

import java.time.Instant;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobarExceptionHandler  {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError err = new StandardError(
			Instant.now(), 
			status.value(), 
			"Recurso não encontrado", 
			e.getMessage(), 
			request.getRequestURI());
		
		return ResponseEntity.status(status).body(err);
	}

}
