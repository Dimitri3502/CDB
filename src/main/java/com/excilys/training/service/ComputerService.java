package com.excilys.training.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.excilys.training.model.Computer;
import com.excilys.training.persistance.ComputerDAO;
import com.excilys.training.servlets.Page;

@Component()
public final class ComputerService {

	private final ComputerDAO computerDAO;

	public ComputerService(ComputerDAO computerDAO) {
		super();
		this.computerDAO = computerDAO;
	}
	

	public long create(Computer computerDTO) {
		return computerDAO.create(computerDTO);
	};

	public long count() {
		return computerDAO.count();
	};

	public long count(String name) {
		return computerDAO.count(name);
	};

	public boolean update(Computer computer) {
		return computerDAO.update(computer);
	};

	public boolean delete(Computer computer) {
		return computerDAO.delete(computer);
	};
	public boolean delete(Long id) {
		return computerDAO.delete(id);
	};
	public Computer findById(Long id) {
		return computerDAO.findById(id);
//		return computer.map(computerMapper::modelToDto);

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
