package com.excilys.training.dto;

public class CompanyDTO extends Dto{
//que des attribu string
	private String name;

	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "CompanyDTO [name=" + name + "]";
	}

	
}
