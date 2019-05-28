package com.excilys.training.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.binding.exception.ComputerNotFoundException;
import com.excilys.training.binding.pagination.Page;
import com.excilys.training.core.Computer;

public interface IComputerService {

	boolean isPresent(Long id);

	long create(Computer computerDTO);

	long count();

	long count(String name);

	boolean update(Computer computer);

	boolean delete(Computer computer);

	boolean delete(Long id);

	Computer findById(Long id) throws ComputerNotFoundException;

	List<Computer> getAll();

	List<Computer> getAll(int limit, int offset);

	List<Computer> getAll(Page page);

}