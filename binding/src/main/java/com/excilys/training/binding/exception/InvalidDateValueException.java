package com.excilys.training.binding.exception;

public class InvalidDateValueException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6660041288950471463L;

	public InvalidDateValueException(String dateValue) {
		super("Invalid date value: " + dateValue + Character.LINE_SEPARATOR + 
				" La date d'introduction doit être entre 1970-01-01 et 2038-01-19 ");
	}
}
