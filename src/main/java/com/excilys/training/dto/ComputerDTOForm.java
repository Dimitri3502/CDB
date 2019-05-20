package com.excilys.training.dto;

import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.excilys.training.validator.Antes;


@Antes(first = "introduced", second = "discontinued", message = "The discontinued date must be after the introduced date")

public class ComputerDTOForm extends Dto{
	
	@NotBlank
	private String computerName;
	
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String introduced;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String discontinued;
	
	private String companyName;
	
	private String companyId;
	
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
		this.introduced = introduced;
	}
	public String getDiscontinued() {
		return discontinued;
	}
	public void setDiscontinued(String discontinued) {
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
