package com.excilys.training.service;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dao.Dao;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.mapper.Mapper;
import com.excilys.training.model.Company;

public class CompanyService extends Service<CompanyDTO, Company>{

	public CompanyService(Mapper<CompanyDTO, Company> m, Dao<Company> d) {
		super(m, d);
		// TODO Auto-generated constructor stub
	}


	@Override
	public List<CompanyDTO> getAll(int limit, int offset){
		CompanyDAO companyDAO = new CompanyDAO();
		List<Company> theCompanyList = companyDAO.getAll(limit, offset);
		List<CompanyDTO> theCompanyDtoList = (List<CompanyDTO>) theCompanyList.stream().map(s -> mapper.modelToDto(s)).collect(Collectors.toList());
		
		return theCompanyDtoList;
	}
	



}
