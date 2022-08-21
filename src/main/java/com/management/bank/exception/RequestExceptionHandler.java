package com.management.bank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RequestExceptionHandler {

	/**
	 * Handles AccountNotFoundException.
	 * 
	 * @param exception AccountNotFoundException.
	 * @return ResponseEntity response.
	 */
	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleAccountNotFoundException(final AccountNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}

	/**
	 * Handles InsufficientBalanceException.
	 * 
	 * @param exception InsufficientBalanceException.
	 * @return ResponseEntity response.
	 */
	@ExceptionHandler(InsufficientBalanceException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<String> handleInsufficientBalanceException(final InsufficientBalanceException exception) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
	}
}
