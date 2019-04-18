package com.excilys.training.exception;

public class InvalidDateValueException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6660041288950471463L;

	public InvalidDateValueException(String dateValue) {
		super("Invalid date value: " + dateValue);
	}
}
