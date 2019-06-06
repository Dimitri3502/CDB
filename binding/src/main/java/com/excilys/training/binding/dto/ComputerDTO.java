package com.excilys.training.binding.dto;

public class ComputerDTO extends Dto{
	

	private String computerName;

	private String introduced;

	private String discontinued;

	private CompanyDTO companyDTO;
	
	public ComputerDTO() {
		super();
	}
	
	public ComputerDTO(String name, String introducedDate, String discontinuedDate, CompanyDTO companyDTO) {
		this(null,name,introducedDate,discontinuedDate,companyDTO);
	}
	
	public ComputerDTO(String id, String name, String introducedDate, String discontinuedDate, CompanyDTO companyDTO) {
		super(id);
		this.computerName = name;
		this.introduced = introducedDate;
		this.discontinued = discontinuedDate;
		this.companyDTO = companyDTO;
	}
	
	public String getName() {
		return computerName;
	}
	public void setName(String name) {
		this.computerName = name;
	}
	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introducedDate) {
		this.introduced = introducedDate;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinuedDate) {
		this.discontinued = discontinuedDate;
	}

	public CompanyDTO getCompanyDTO() {
		return companyDTO;
	}
	public void setCompanyDTO(CompanyDTO companyDTO) {
		this.companyDTO = companyDTO;
	}
	@Override
	public String toString() {
		return "ComputerDTO [id = " + getId() + ", name=" + computerName + ", introducedDate=" + introduced + ", discontinuedDate="
				+ discontinued + ", CompanyDTO=" + companyDTO.toString() + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((companyDTO == null) ? 0 : companyDTO.hashCode());
		result = prime * result + ((discontinued == null) ? 0 : discontinued.hashCode());
		result = prime * result + ((introduced == null) ? 0 : introduced.hashCode());
		result = prime * result + ((computerName == null) ? 0 : computerName.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDTO other = (ComputerDTO) obj;
		if (companyDTO == null) {
			if (other.companyDTO != null)
				return false;
		} else if (!companyDTO.equals(other.companyDTO))
			return false;
		if (discontinued == null) {
			if (other.discontinued != null)
				return false;
		} else if (!discontinued.equals(other.discontinued))
			return false;
		if (introduced == null) {
			if (other.introduced != null)
				return false;
		} else if (!introduced.equals(other.introduced))
			return false;
		if (computerName == null) {
			if (other.computerName != null)
				return false;
		} else if (!computerName.equals(other.computerName))
			return false;
		return true;
	}

}
