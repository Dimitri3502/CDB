package com.excilys.training.persistance.exception;

public class ComputerNotDeletedException extends RuntimeException   {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1006058175751937740L;

	public ComputerNotDeletedException(Long id) {
		super("une erreur est survenue lord de la suppression des ordinateurs dont le fabriquant est id = " + id );
		// TODO Auto-generated constructor stub
	}


}
