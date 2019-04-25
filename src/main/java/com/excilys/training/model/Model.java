package com.excilys.training.model;

public class Model {


	public Model() {
		super();
	}

	public Model(long id) {
		super();
		this.id = id;
	}

	private long id;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
}
