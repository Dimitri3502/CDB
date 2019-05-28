package com.excilys.training.binding.exception;

public class CompanyNotDeletedException extends RuntimeException   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1006058175751937740L;

	public CompanyNotDeletedException(Long id) {
		super("une erreur est survenue lord de la suppression du fabriquant id = " + id );
		// TODO Auto-generated constructor stub
	}


}
