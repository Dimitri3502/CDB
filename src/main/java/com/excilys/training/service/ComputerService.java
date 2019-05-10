package com.excilys.training.service;

import java.util.List;
import java.util.Optional;

import com.excilys.training.model.Computer;
import com.excilys.training.persistance.CompanyDAO;
import com.excilys.training.persistance.ComputerDAO;
import com.excilys.training.servlets.Page;

public final class ComputerService {

	private static ComputerService instance = null;
	private final ComputerDAO computerDAO = ComputerDAO.getInstance();
	private final CompanyDAO companyDAO = CompanyDAO.getInstance();

	private ComputerService() {
	}

	public final static ComputerService getInstance() {
		if (ComputerService.instance == null) {

			if (ComputerService.instance == null) {
				ComputerService.instance = new ComputerService();
			}

		}
		return ComputerService.instance;
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

	public long getLastIdInserted() {
		return computerDAO.getLastIdInserted();
	};

	public boolean update(Computer computer) {
		return computerDAO.update(computer);
	};

	public boolean delete(Computer computer) {
		return computerDAO.delete(computer);
	};

	public Optional<Computer> findById(Long id) {
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
