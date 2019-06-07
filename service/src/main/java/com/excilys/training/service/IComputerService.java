package com.excilys.training.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.excilys.training.binding.exception.ComputerNotFoundException;
import com.excilys.training.binding.pagination.Page;
import com.excilys.training.core.Computer;

public interface IComputerService {

	boolean isPresent(Long id);

	long create(Computer computerDTO);

	long count();

	long count(String name);

	boolean update(Computer computer);

	void delete(Computer computer);

	void delete(Long id);

	Computer findById(Long id) throws ComputerNotFoundException;

	List<Computer> getAll();

	List<Computer> getAll(int page, int size);

	List<Computer> getAll(Page page);

	org.springframework.data.domain.Page<Computer> getAll(String name, Pageable page);

}