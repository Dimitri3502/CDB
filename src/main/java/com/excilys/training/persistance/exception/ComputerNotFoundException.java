package com.excilys.training.persistance.exception;

public class ComputerNotFoundException extends NotFoundException   {

	public ComputerNotFoundException(Long id) {
		super("l'ordinateur id = " + id + " n'hexiste pas");
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -4637047028132420585L;

}
