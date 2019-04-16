package com.excilys.training.dto;

import java.util.Date;

public class ComputerDTO {
	private String name;
	private String introducedDate;
	private String discontinuedDate;
	private String idCompany;

	public ComputerDTO(String name, String introducedDate, String discontinuedDate, String idCompany) {
		super();
		this.name = name;
		this.introducedDate = introducedDate;
		this.discontinuedDate = discontinuedDate;
		this.idCompany = idCompany;
	}

	public ComputerDTO() {
		// TODO Auto-generated constructor stub
	}

}
