package com.excilys.training.mapper;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.model.Computer;

public class ComputerMapper {
	
	public static Computer map() {
		Computer theComputer = new Computer();
		return theComputer;
	}

	public static ComputerDTO unMap() {
		ComputerDTO theComputerDTO = new ComputerDTO();
		return theComputerDTO;
	}
	
}
