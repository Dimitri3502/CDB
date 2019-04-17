package com.excilys.training.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dao.ComputerDAO;
import com.excilys.training.dao.Dao;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.CompanyMapper;
import com.excilys.training.mapper.Mapper;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;

public class CompanyService extends Service<CompanyDTO, Company>{

	public CompanyService(Mapper<CompanyDTO, Company> m, Dao<Company> d) {
		super(m, d);
		// TODO Auto-generated constructor stub
	}


	@Override
	public List<CompanyDTO> getAll() {
		CompanyDAO companyDAO = new CompanyDAO();
		List<Company> theCompanyList = companyDAO.getAll();
		List<CompanyDTO> theCompanyDtoList = (List<CompanyDTO>) theCompanyList.stream().map(s -> mapper.modelToDto(s)).collect(Collectors.toList());
		
		return theCompanyDtoList;
	}
	



}
