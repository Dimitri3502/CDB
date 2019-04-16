package com.excilys.training.model;

/**
 * @author dimitri
 *
 */

public class Company {
	private long id;
	private String Name;

	public Company(int id, String name) {
		super();
		this.id = id;
		Name = name;
	}

	public Company() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

}
