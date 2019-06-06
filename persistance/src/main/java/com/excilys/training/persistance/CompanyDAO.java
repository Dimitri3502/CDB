package com.excilys.training.persistance;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.excilys.training.core.Company;

@Repository
public interface CompanyDAO extends CrudRepository<Company, Long>{
	List<Company> findByName(String name);
}
