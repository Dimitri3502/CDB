package com.excilys.training.model;

public class Model {


	public Model() {
		super();
	}

	public Model(Long id) {
		super();
		this.id = id;
	}

	private Long id;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
