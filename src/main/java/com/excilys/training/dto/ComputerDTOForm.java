package com.excilys.training.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import com.excilys.training.validator.DatesConstraint;


@DatesConstraint(first = "introduced", second = "discontinued")

public class ComputerDTOForm extends Dto{
	
	@NotBlank
	private String computerName;
	
	
	@Pattern(regexp=DATE_PATTERN)
	private String introduced;
	
	@Pattern(regexp=DATE_PATTERN)
	private String discontinued;
	
	private String companyName;
	
	private String companyId;
	
	private static final String DATE_PATTERN = "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
		      + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
		      + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
		      + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$";
	
	public String getComputerName() {
		return computerName;
	}
	public void setComputerName(String name) {
		this.computerName = name;
	}


	public String getIntroduced() {
		return introduced;
	}
	public void setIntroduced(String introduced) {
		if (introduced!= null && introduced.isEmpty()) {
			introduced = null;
		}
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
		if (discontinued!= null && discontinued.isEmpty()) {
			discontinued = null;
		}
		this.discontinued = discontinued;
	}
	@Override
	public String toString() {
		return "ComputerDTO [id = " + id + ", name=" + computerName + ", introducedDate=" + introduced + ", discontinuedDate="
				+ discontinued + "]";
	}
	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(companyId, companyName, computerName, discontinued, introduced);
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDTOForm other = (ComputerDTOForm) obj;
		return Objects.equals(companyId, other.companyId) && Objects.equals(companyName, other.companyName)
				&& Objects.equals(computerName, other.computerName) && Objects.equals(discontinued, other.discontinued)
				&& Objects.equals(introduced, other.introduced);
	}

}
