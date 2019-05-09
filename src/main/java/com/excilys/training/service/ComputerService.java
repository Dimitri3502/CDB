package com.excilys.training.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.mapper.ComputerMapper;
import com.excilys.training.model.Computer;
import com.excilys.training.persistance.CompanyDAO;
import com.excilys.training.persistance.ComputerDAO;
import com.excilys.training.persistance.OrderByChamp;
import com.excilys.training.persistance.OrderByDirection;

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
	public long count(String name){
		return computerDAO.count(name);
	};
	public long getLastIdInserted(){
		return computerDAO.getLastIdInserted();
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
	public List<ComputerDTO> getAll() {
		return allModelToDTO(computerDAO.getAll());
	}
	public List<ComputerDTO> getAll(int limit, int offset) {
		return allModelToDTO(computerDAO.getAll(limit, offset));
	}

	public List<ComputerDTO> getAll(int limit, int offset, String name, OrderByChamp orderBy, OrderByDirection orderDirection) {
		return allModelToDTO(computerDAO.getAll(limit, offset, name, orderBy, orderDirection));
	}
	
	private List<ComputerDTO> allModelToDTO(List<Computer> theComputerList) {
		List<ComputerDTO> theComputerDtoList = (List<ComputerDTO>) theComputerList.stream().map(s -> computerMapper.modelToDto(s)).collect(Collectors.toList());
		return theComputerDtoList;
	}
}
