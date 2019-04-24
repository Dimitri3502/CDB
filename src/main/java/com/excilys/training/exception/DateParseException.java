package com.excilys.training.exception;

public class DateParseException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6057018724223089802L;
	
	public DateParseException(String dateValue) {
		super("Invalid date format: " + dateValue);
	}

}
