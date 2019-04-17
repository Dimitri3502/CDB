package com.excilys.training.mapper;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.model.Computer;

public class ComputerMapper extends Mapper<ComputerDTO, Computer> {

	@Override
	public Computer dtoToModel(ComputerDTO computerDTO) {
		Computer theComputer = new Computer();
		return theComputer;
	}

	@Override
	public ComputerDTO modelToDto(Computer computer) {
		ComputerDTO theComputerDTO = new ComputerDTO();
		theComputerDTO.setId(computer.getId());
		theComputerDTO.setName(computer.getName());
		if (computer.getIntroducedDate() != null) {
			theComputerDTO.setIntroducedDate(computer.getIntroducedDate().toString());
		} 
		if (computer.getDiscontinuedDate()!= null) {
			theComputerDTO.setDiscontinuedDate(computer.getDiscontinuedDate().toString());
		}
		theComputerDTO.setIdCompany(computer.getCompany().getName());
		return theComputerDTO;
	}

}
