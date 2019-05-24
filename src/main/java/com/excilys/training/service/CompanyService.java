package com.excilys.training.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.exception.CompanyNotDeletedException;
import com.excilys.training.exception.CompanyNotFoundException;
import com.excilys.training.model.Company;
import com.excilys.training.persistance.CompanyDAO;
import com.excilys.training.persistance.ComputerDAO;

@Service
@Transactional(readOnly = true)
public class CompanyService {

	private final CompanyDAO companyDAO;
	private final ComputerDAO computerDAO;

	public CompanyService(CompanyDAO companyDAO, ComputerDAO computerDAO) {
		super();
		this.companyDAO = companyDAO;
		this.computerDAO = computerDAO;
	}

	public boolean isPresent(Long id) {
		return findById(id) != null;
	};

	public Company findById(Long id) {
		return this.companyDAO.findById(id);
	};

	public List<Company> getAll() {
		return companyDAO.getAll();
	}

	public List<Company> getAll(int limit, int offset) {
		return companyDAO.getAll(limit, offset);
	}

	@Transactional
	public boolean delete(Long id) {
		if (isPresent(id)) {
			computerDAO.deleteByCompanyId(id);
			companyDAO.delete(id);
			return true;

		} else {
			throw new CompanyNotFoundException(id);

		}

	}
}
