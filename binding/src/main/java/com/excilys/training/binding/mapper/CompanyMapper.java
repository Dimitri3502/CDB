package com.excilys.training.binding.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.excilys.training.binding.dto.CompanyDTO;
import com.excilys.training.binding.dto.ComputerDTO;
import com.excilys.training.core.Company;
import com.excilys.training.core.Computer;

@Component
public class CompanyMapper extends Mapper<CompanyDTO, Company>{
	private final Logger logger = LogManager.getLogger(getClass());

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
		try {
			theCompanyDTO.setId(Long.toString(company.getId()));
			theCompanyDTO.setName(company.getName());
		} catch (NullPointerException e) {
			logger.debug("company is null");
		}
		return theCompanyDTO;
	}
	
	@Override
	public List<CompanyDTO> allModelToDTO(List<Company> theCompanyList) {
		return (List<CompanyDTO>) theCompanyList.stream().map(s -> modelToDto(s)).collect(Collectors.toList());

	}
	
}
