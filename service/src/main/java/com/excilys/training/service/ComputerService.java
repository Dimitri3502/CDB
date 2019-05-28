package com.excilys.training.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.binding.exception.ComputerNotFoundException;
import com.excilys.training.binding.pagination.Page;
import com.excilys.training.core.Computer;
import com.excilys.training.persistance.ComputerDAO;

@Service
@Transactional(readOnly = true)
public class ComputerService implements IComputerService {

	private final ComputerDAO computerDAO;
	private final Logger logger = LogManager.getLogger(getClass());
	public ComputerService(ComputerDAO computerDAO) {
		super();
		this.computerDAO = computerDAO;
	}
	
	@Override
	public boolean isPresent(Long id) {
		try {
			findById(id) ;
			return true;
		} catch (ComputerNotFoundException e) {
			logger.warn(e.getMessage());
		}
		return false;
	};
	
	@Override
	@Transactional
	public long create(Computer computerDTO) {
		return computerDAO.create(computerDTO);
	};

	@Override
	public long count() {
		return computerDAO.count();
	};

	@Override
	public long count(String name) {
		return computerDAO.count(name);
	};
	@Override
	@Transactional
	public boolean update(Computer computer) {
		return computerDAO.update(computer);
	};
	@Override
	@Transactional
	public boolean delete(Computer computer) {
		return computerDAO.delete(computer);
	};
	@Override
	@Transactional
	public boolean delete(Long id) {
		return computerDAO.delete(id);
	};
	@Override
	public Computer findById(Long id) throws ComputerNotFoundException {
		return computerDAO.findById(id);

	};

	@Override
	public List<Computer> getAll() {
		return computerDAO.getAll();
	}

	@Override
	public List<Computer> getAll(int limit, int offset) {
		return computerDAO.getAll(limit, offset);
	}

	@Override
	public List<Computer> getAll(Page page) {
		return computerDAO.getAll(page);
	}

}
