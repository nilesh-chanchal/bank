package com.management.bank.exception;

public class InsufficientBalanceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientBalanceException(final String message) {
		super(message);
	}

}
