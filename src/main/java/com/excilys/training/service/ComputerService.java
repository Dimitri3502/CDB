package com.excilys.training.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dao.ComputerDAO;
import com.excilys.training.dao.Dao;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.ComputerNotFoundException;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.mapper.Mapper;
import com.excilys.training.model.Computer;

public final class ComputerService {
	
	private static ComputerService instance = null;
	private final ComputerDAO computerDAO = ComputerDAO.getInstance();
	private final CompanyDAO companyDAO = CompanyDAO.getInstance();
	private final ComputerMapper computerMapper = ComputerMapper.getInstance(companyDAO);

	private ComputerService() {
		// TODO Auto-generated constructor stub
	}
	public final static ComputerService getInstance()  {
		if (ComputerService.instance == null) {
             
              if (ComputerService.instance == null) {
            	  ComputerService.instance = new ComputerService();
              }
            
         }
         return ComputerService.instance;
	}
	
	public long create(ComputerDTO computerDTO){
		return computerDAO.create(computerMapper.dtoToModel(computerDTO));
	};

	public long count(){
		return computerDAO.count();
	};
	
	public boolean update(ComputerDTO computerDTO){
		return computerDAO.update(computerMapper.dtoToModel(computerDTO));
	};
	
	public boolean delete(ComputerDTO computerDTO){
		return computerDAO.delete(computerMapper.dtoToModel(computerDTO));
	};
	
	public Optional<ComputerDTO> findById(Long id) {
		Optional<Computer> computer = computerDAO.findById(id);
		return computer.map(computerMapper::modelToDto);
		
	};

	public List<ComputerDTO> getAll(int limit, int offset) {
		List<Computer> theComputerList = computerDAO.getAll(limit, offset);
		List<ComputerDTO> theComputerDtoList = (List<ComputerDTO>) theComputerList.stream().map(s -> computerMapper.modelToDto(s)).collect(Collectors.toList());

		return theComputerDtoList;
	}
}
