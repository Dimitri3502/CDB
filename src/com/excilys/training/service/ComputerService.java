package com.excilys.training.service;

import java.util.List;

import com.excilys.training.dao.ComputerDAO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.model.Computer;

public class ComputerService {
private ComputerDAO computerDAO;
	
	public String listComputers() {
		computerDAO = new ComputerDAO();
		List<Computer> theComputerList = computerDAO.getAll();
		return theComputerList.toString();
	}

	public String showComputer(int id) {
//		String theComputer = computerDAO.getComputer();
		return null;

	}

	public String createComputer(ComputerDTO computerDTO) {
//		Computer theComputer = ComputerMapper.
//		computerDAO.createComputer();
		return null;

	}

	public String updateComputer() {

		return null;

	}

	public String deleteComputer() {

		return null;

	}
}
