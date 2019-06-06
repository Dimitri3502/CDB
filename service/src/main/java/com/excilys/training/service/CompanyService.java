package com.excilys.training.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.binding.exception.CompanyNotFoundException;
import com.excilys.training.binding.mapper.FindCompanyById;
import com.excilys.training.core.Company;
import com.excilys.training.persistance.CompanyDAO;
import com.excilys.training.persistance.ComputerDAO;

@Service
@Transactional(readOnly = true)
public class CompanyService implements FindCompanyById, ICompanyService{
	private final Logger logger = LogManager.getLogger(getClass());
	private final CompanyDAO companyDAO;
	private final ComputerDAO computerDAO;

	public CompanyService(CompanyDAO companyDAO, ComputerDAO computerDAO) {
		super();
		this.companyDAO = companyDAO;
		this.computerDAO = computerDAO;
	}

	@Override
	public boolean isPresent(Long id) {
		try {
			findById(id) ;
			return true;
		} catch (CompanyNotFoundException e) {
			logger.warn(e.getMessage());
		}
		return false;
	};

	@Override
	public Company findById(Long id) throws CompanyNotFoundException {
		if (this.companyDAO.findById(id).isPresent()) {
			return this.companyDAO.findById(id).get();
		}
		else {
			throw new CompanyNotFoundException(id);
		}
	};

	@Override
	public List<Company> getAll() {
		List<Company> companies = new ArrayList<>();
		companyDAO.findAll().forEach(companies::add);
		return companies;
	}


	@Override
	@Transactional
	public boolean delete(Long id) throws CompanyNotFoundException {
		if (isPresent(id)) {
			computerDAO.deleteByCompanyId(id);
			companyDAO.deleteById(id);
			return true;

		} else {
			throw new CompanyNotFoundException(id);

		}

	}
}
