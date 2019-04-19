package com.excilys.training.service;

import java.util.List;
import java.util.stream.Collectors;

import com.excilys.training.dao.ComputerDAO;
import com.excilys.training.dao.Dao;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.ComputerNotFoundException;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.mapper.Mapper;
import com.excilys.training.model.Computer;

public class ComputerService extends Service<ComputerDTO, Computer> {
	

	public ComputerService(Mapper<ComputerDTO, Computer> m, Dao<Computer> d) {
		super(m, d);
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<ComputerDTO> getAll(int limit, int offset) throws InvalidDiscontinuedDate{
		ComputerDAO computerDAO = new ComputerDAO();
		List<Computer> theComputerList = computerDAO.getAll(limit, offset);
		List<ComputerDTO> theComputerDtoList = (List<ComputerDTO>) theComputerList.stream().map(s -> mapper.modelToDto(s)).collect(Collectors.toList());

		return theComputerDtoList;
	}
}
