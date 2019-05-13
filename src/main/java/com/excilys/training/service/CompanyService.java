package com.excilys.training.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.exception.CompanyNotDeletedException;
import com.excilys.training.exception.CompanyNotFoundException;
import com.excilys.training.mapper.CompanyMapper;
import com.excilys.training.model.Company;
import com.excilys.training.persistance.CompanyDAO;

@Component()
public class CompanyService {

	private final CompanyDAO companyDAO;
	private final CompanyMapper companyMapper;


	public CompanyService(CompanyDAO companyDAO, CompanyMapper companyMapper) {
		super();
		this.companyDAO = companyDAO;
		this.companyMapper = companyMapper;
	}

	public boolean isPresent(Long id) {
		return findById(id).isPresent();
	};

	public Optional<Company> findById(Long id) {
		return this.companyDAO.findById(id);
	};

	public List<Company> getAll() {
		return companyDAO.getAll();
//		List<CompanyDTO> theCompanyDtoList = (List<CompanyDTO>) theCompanyList.stream()
//				.map(s -> companyMapper.modelToDto(s)).collect(Collectors.toList());
//
//		return theCompanyDtoList;
	}

	public List<Company> getAll(int limit, int offset) {
		return companyDAO.getAll(limit, offset);
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
