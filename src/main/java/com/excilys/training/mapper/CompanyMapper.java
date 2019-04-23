package com.excilys.training.mapper;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.model.Company;

public class CompanyMapper extends Mapper<CompanyDTO, Company>{

	private static CompanyMapper instance = null;
	
	private CompanyMapper() {
		super();
	}

	public final static CompanyMapper getInstance()  {
		if (CompanyMapper.instance == null) {
             
              if (CompanyMapper.instance == null) {
            	  CompanyMapper.instance = new CompanyMapper();
              }
            
         }
         return CompanyMapper.instance;
	}
	@Override
	public Company dtoToModel(CompanyDTO companyDTO) {
		Company theCompany = new Company();
		theCompany.setId(Long.parseLong(companyDTO.getId()));
		theCompany.setName(companyDTO.getName());
		return theCompany;
	}

	@Override
	public CompanyDTO modelToDto(Company company) {
		CompanyDTO theCompanyDTO = new CompanyDTO();
		theCompanyDTO.setId(Long.toString(company.getId()));
		theCompanyDTO.setName(company.getName());
		return theCompanyDTO;
	}
	
}
