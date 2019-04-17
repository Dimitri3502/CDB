package com.excilys.training.mapper;

import java.time.LocalDate;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;

public class ComputerMapper extends Mapper<ComputerDTO, Computer> {

	public ComputerMapper(CompanyDAO companyDAO) {
		super();
		this.companyDAO = companyDAO;
	}

	private CompanyDAO companyDAO;

	@Override
	public Computer dtoToModel(ComputerDTO computerDTO) {
		Computer theComputer = new Computer();
		theComputer.setId(Long.parseLong(computerDTO.getId()));
		theComputer.setName(computerDTO.getName());
		if (computerDTO.getIntroducedDate() != null) {
			theComputer.setIntroducedDate(LocalDate.parse(computerDTO.getIntroducedDate()));
		}
		if (computerDTO.getDiscontinuedDate() != null) {
			theComputer.setDiscontinuedDate(LocalDate.parse(computerDTO.getDiscontinuedDate()));
		}
		Company company = companyDAO.findById(Long.parseLong(computerDTO.getIdCompany()));

		theComputer.setCompany(company);
		return theComputer;
	}
 
	@Override
	public ComputerDTO modelToDto(Computer computer) {
		ComputerDTO theComputerDTO = new ComputerDTO();
		theComputerDTO.setId(Long.toString(computer.getId()));
		theComputerDTO.setName(computer.getName());
		if (computer.getIntroducedDate() != null) {
			theComputerDTO.setIntroducedDate(computer.getIntroducedDate().toString());
		}
		if (computer.getDiscontinuedDate() != null) {
			theComputerDTO.setDiscontinuedDate(computer.getDiscontinuedDate().toString());
		}
		theComputerDTO.setIdCompany(Long.toString(computer.getCompany().getId()));
		return theComputerDTO;
	}

}
