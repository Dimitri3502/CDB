package com.excilys.training.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.excilys.training.binding.exception.ComputerNotFoundException;
import com.excilys.training.binding.pagination.Page;
import com.excilys.training.binding.pagination.ENUMS.OrderByChamp;
import com.excilys.training.binding.pagination.ENUMS.OrderByDirection;
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
			findById(id);
			return true;
		} catch (ComputerNotFoundException e) {
			logger.warn(e.getMessage());
		}
		return false;
	};

	@Override
	@Transactional
	public long create(Computer computer) {
		computerDAO.save(computer);
		return computer.getId();
	};

	@Override
	public long count() {
		return computerDAO.count();
	};

	@Override
	public long count(String name) {
		return computerDAO.countByNameLikeOrCompanyNameLike(name, name);
	};

	@Override
	@Transactional
	public boolean update(Computer computer) {
		computerDAO.save(computer);
		return false;
	};

	@Override
	@Transactional
	public void delete(Computer computer) {
		computerDAO.delete(computer);
	};

	@Override
	@Transactional
	public void delete(Long id) {
		computerDAO.deleteById(id);
	};

	@Override
	public Computer findById(Long id) throws ComputerNotFoundException {
		if (this.computerDAO.findById(id).isPresent()) {
			return this.computerDAO.findById(id).get();
		} else {
			throw new ComputerNotFoundException(id);
		}
	};

	@Override
	public List<Computer> getAll() {
		List<Computer> computers = new ArrayList<>();
		computerDAO.findAll().forEach(computers::add);
		return computers;
	}

	@Override
	public List<Computer> getAll(int page, int limit) {
		List<Computer> computers = new ArrayList<>();
		computerDAO.findAll(PageRequest.of(page, limit)).forEach(computers::add);
		return computers;
	}

	@Override
	public List<Computer> getAll(Page page) {
		List<Computer> computers = new ArrayList<>();
		String name = page.getFilter();
		Pageable p = pageable(page);
		computerDAO.findAllByNameLikeOrCompanyNameLike(name,name, p).forEach(computers::add);
		return computers;
	}
	private Pageable pageable(Page page) {
		Pageable p=  PageRequest.of(page.getCurrentPageNumber(), 
				page.getLimit(), Direction.valueOf(map(page.getOrderDirection())), map(page.getOrderBy()));

		return p;
	}

	private String map(OrderByDirection c) {
		switch (c) {
		default:
		case ASC:
			return "ASC";
		case DESC:
			return "DESC";
		}
	}

	private String map(OrderByChamp c) {
		switch (c) {
		default:
		case ID:
			return "id";
		case NAME:
			return "name";
		case INTRODUCED:
			return "introduced";
		case DISCONTINUED:
			return "discontinued";
		case COMPANY:
			return "company.name";
		}
	}

}
