package com.excilys.training.dto;

public class ComputerDTO extends Dto{
	private String name;
	private String introducedDate;
	private String discontinuedDate;
	private CompanyDTO companyDTO;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroducedDate() {
		return introducedDate;
	}
	public void setIntroducedDate(String introducedDate) {
		this.introducedDate = introducedDate;
	}
	public String getDiscontinuedDate() {
		return discontinuedDate;
	}
	public void setDiscontinuedDate(String discontinuedDate) {
		this.discontinuedDate = discontinuedDate;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}
	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}
	@Override
	public String toString() {
		return "ComputerDTO [id = " + id + ", name=" + name + ", introducedDate=" + introducedDate + ", discontinuedDate="
				+ discontinuedDate + ", CompanyDTO=" + companyDTO.toString() + "]";
	}

}
