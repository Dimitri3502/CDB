package com.excilys.training.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dao.Dao;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.mapper.CompanyMapper;
import com.excilys.training.mapper.Mapper;
import com.excilys.training.model.Company;

public class CompanyService {

	private static CompanyService instance = null;
	private final CompanyDAO companyDAO = CompanyDAO.getInstance();
	private final CompanyMapper companyMapper = CompanyMapper.getInstance();
	
	private CompanyService() {
		// TODO Auto-generated constructor stub
	}

	public static CompanyService getInstance() {
		if (instance == null) {
			instance = new CompanyService();
		}
		return instance;
	}
	
	public Optional<CompanyDTO> findById(Long id) {
		return this.companyDAO.findById(id).map(companyMapper::modelToDto);
	};
	
	public List<CompanyDTO> getAll(int limit, int offset) {
		List<Company> theCompanyList = companyDAO.getAll(limit, offset);
		List<CompanyDTO> theCompanyDtoList = (List<CompanyDTO>) theCompanyList.stream().map(s -> companyMapper.modelToDto(s))
				.collect(Collectors.toList());

		return theCompanyDtoList;
	}


}
