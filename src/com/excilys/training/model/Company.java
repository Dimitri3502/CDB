package com.excilys.training.model;

/**
 * @author dimitri
 *
 */

public class Company extends Model{
	

	private String Name;

	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	@Override
	public String toString() {
		return "Company [Name=" + Name + "]";
	}


}
