package com.excilys.training.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.exception.CompanyNotDeletedException;
import com.excilys.training.exception.CompanyNotFoundException;
import com.excilys.training.mapper.CompanyMapper;
import com.excilys.training.model.Company;
import com.excilys.training.persistance.CompanyDAO;

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

	public boolean isPresent(Long id) {
		return findById(id).isPresent();
	};

	public Optional<CompanyDTO> findById(Long id) {
		return this.companyDAO.findById(id).map(companyMapper::modelToDto);
	};

	public List<CompanyDTO> getAll() {
		List<Company> theCompanyList = companyDAO.getAll();
		List<CompanyDTO> theCompanyDtoList = (List<CompanyDTO>) theCompanyList.stream()
				.map(s -> companyMapper.modelToDto(s)).collect(Collectors.toList());

		return theCompanyDtoList;
	}

	public List<CompanyDTO> getAll(int limit, int offset) {
		List<Company> theCompanyList = companyDAO.getAll(limit, offset);
		List<CompanyDTO> theCompanyDtoList = (List<CompanyDTO>) theCompanyList.stream()
				.map(s -> companyMapper.modelToDto(s)).collect(Collectors.toList());

		return theCompanyDtoList;
	}

	public boolean delete(Long id) throws CompanyNotFoundException, CompanyNotDeletedException {
		if (isPresent(id)) {
			if (this.companyDAO.delete(id)) {
				return true;
			} else {
				throw new CompanyNotDeletedException(id);
			}
		} else {
			throw new CompanyNotFoundException(id);

		}

	}
}
