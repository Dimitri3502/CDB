package com.excilys.training.binding.mapper;

import com.excilys.training.binding.exception.CompanyNotFoundException;
import com.excilys.training.core.Company;

public interface FindCompanyById {
 public Company findById(Long id) throws CompanyNotFoundException;
}
