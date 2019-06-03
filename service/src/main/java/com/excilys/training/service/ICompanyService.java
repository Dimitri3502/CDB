package com.excilys.training.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.binding.exception.CompanyNotFoundException;
import com.excilys.training.core.Company;

public interface ICompanyService {

	boolean isPresent(Long id);

	Company findById(Long id) throws CompanyNotFoundException;

	List<Company> getAll();

	boolean delete(Long id) throws CompanyNotFoundException;

}