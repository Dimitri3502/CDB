package com.excilys.training.persistance;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.excilys.training.core.Computer;

@Repository
public interface ComputerDAO extends PagingAndSortingRepository<Computer, Long> {
	void deleteByCompanyId(long id);
	
	List<Computer> findByName(String name);
	
	List<Computer> findAllByName(String name, Pageable pageable);

	long countByName(String name);
}
