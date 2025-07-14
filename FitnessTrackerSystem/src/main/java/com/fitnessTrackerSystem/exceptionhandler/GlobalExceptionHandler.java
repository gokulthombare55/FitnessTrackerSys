package com.fitnessTrackerSystem.exceptionhandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.fitnessTrackerSystem.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
		return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
		ErrorResponse errorResponse = new ErrorResponse();
		return new ResponseEntity<>(errorResponse, status);
	}
}
