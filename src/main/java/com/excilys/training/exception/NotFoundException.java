package com.excilys.training.exception;

public class NotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5761892313790076495L;

	public NotFoundException(Long id) {
		super("Id invalid : " + id);
	}

	public NotFoundException(String message) {
		// TODO Auto-generated constructor stub
		super(message);
	}
}
