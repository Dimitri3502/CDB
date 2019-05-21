package com.excilys.training.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.controller.web.Page;
import com.excilys.training.model.Computer;
import com.excilys.training.persistance.ComputerDAO;

@Service
@Transactional(readOnly = true)
public class ComputerService {

	private final ComputerDAO computerDAO;

	public ComputerService(ComputerDAO computerDAO) {
		super();
		this.computerDAO = computerDAO;
	}
	
	@Transactional
	public long create(Computer computerDTO) {
		return computerDAO.create(computerDTO);
	};

	public long count() {
		return computerDAO.count();
	};

	public long count(String name) {
		return computerDAO.count(name);
	};
	@Transactional
	public boolean update(Computer computer) {
		return computerDAO.update(computer);
	};
	@Transactional
	public boolean delete(Computer computer) {
		return computerDAO.delete(computer);
	};
	@Transactional
	public boolean delete(Long id) {
		return computerDAO.delete(id);
	};
	public Computer findById(Long id) {
		return computerDAO.findById(id);

	};

	public List<Computer> getAll() {
		return computerDAO.getAll();
	}

	public List<Computer> getAll(int limit, int offset) {
		return computerDAO.getAll(limit, offset);
	}

	public List<Computer> getAll(Page page) {
		return computerDAO.getAll(page);
	}

}
