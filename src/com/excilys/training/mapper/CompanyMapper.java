package com.excilys.training.mapper;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.model.Company;

public class CompanyMapper extends Mapper<CompanyDTO, Company>{

	@Override
	public Company dtoToModel(CompanyDTO companyDTO) {
		Company theCompany = new Company();
		return theCompany;
	}

	@Override
	public CompanyDTO modelToDto(Company company) {
		CompanyDTO theCompanyDTO = new CompanyDTO();
		theCompanyDTO.setId(company.getId());
		theCompanyDTO.setName(company.getName());
		return theCompanyDTO;
	}
	
}
