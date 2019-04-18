package com.excilys.training.model;

import java.time.LocalDate;

/**
 * @author dimitri
 *
 */
public class Computer extends Model{

	private String name;
	private LocalDate introducedDate;
	private LocalDate discontinuedDate;
	private Company company;


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getIntroducedDate() {
		return introducedDate;
	}

	public void setIntroducedDate(LocalDate introducedDate) {
		this.introducedDate = introducedDate;
	}

	public LocalDate getDiscontinuedDate() {
		return discontinuedDate;
	}

	public void setDiscontinuedDate(LocalDate discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}


	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "Computer [name=" + name + ", introducedDate=" + introducedDate + ", discontinuedDate="
				+ discontinuedDate + ", company=" + company + "]";
	}



	
}
