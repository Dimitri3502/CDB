package com.excilys.training.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;

@Component
public class CompanyMapper extends Mapper<CompanyDTO, Company>{


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
		if (!(company.getId()==null)) {
			theCompanyDTO.setId(Long.toString(company.getId()));
		}
		if (!(company.getName()==null)) {
			theCompanyDTO.setName(company.getName());
		}
		return theCompanyDTO;
	}
	
	@Override
	public List<CompanyDTO> allModelToDTO(List<Company> theCompanyList) {
		return (List<CompanyDTO>) theCompanyList.stream().map(s -> modelToDto(s)).collect(Collectors.toList());

	}
	
}
