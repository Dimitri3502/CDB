package com.excilys.training.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.core.Company;
import com.excilys.training.persistance.CompanyDAO;
import com.excilys.training.persistance.ComputerDAO;
import com.excilys.training.persistance.exception.CompanyNotFoundException;

@Service
@Transactional(readOnly = true)
public class CompanyService {
	private final Logger logger = LogManager.getLogger(getClass());
	private final CompanyDAO companyDAO;
	private final ComputerDAO computerDAO;

	public CompanyService(CompanyDAO companyDAO, ComputerDAO computerDAO) {
		super();
		this.companyDAO = companyDAO;
		this.computerDAO = computerDAO;
	}

	public boolean isPresent(Long id) {
		try {
			findById(id) ;
			return true;
		} catch (CompanyNotFoundException e) {
			logger.warn(e.getMessage());
		}
		return false;
	};

	public Company findById(Long id) throws CompanyNotFoundException {
		return this.companyDAO.findById(id);
	};

	public List<Company> getAll() {
		return companyDAO.getAll();
	}

	public List<Company> getAll(int limit, int offset) {
		return companyDAO.getAll(limit, offset);
	}

	@Transactional
	public boolean delete(Long id) throws CompanyNotFoundException {
		if (isPresent(id)) {
			computerDAO.deleteByCompanyId(id);
			companyDAO.delete(id);
			return true;

		} else {
			throw new CompanyNotFoundException(id);

		}

	}
}
