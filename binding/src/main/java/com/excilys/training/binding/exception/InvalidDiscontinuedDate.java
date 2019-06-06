package com.excilys.training.binding.exception;

public class InvalidDiscontinuedDate extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1098502835036030946L;
	public InvalidDiscontinuedDate() {
		super("Date de fin inférieure à la date de début");
	}
}
