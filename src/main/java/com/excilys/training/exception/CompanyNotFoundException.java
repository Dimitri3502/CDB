package com.excilys.training.exception;

public class CompanyNotFoundException extends NotFoundException   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1006058175751937740L;

	public CompanyNotFoundException(Long id) {
		super("Le fabriquant id = " + id + " n'existe pas");
		// TODO Auto-generated constructor stub
	}


}
