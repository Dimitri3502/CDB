package com.excilys.training.mapper;

import java.time.LocalDate;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;

public class ComputerMapper extends Mapper<ComputerDTO, Computer> {

	public ComputerMapper(CompanyDAO companyDAO) {
		super();
		this.companyDAO = companyDAO;
	}

	private CompanyDAO companyDAO;

	@Override
	public Computer dtoToModel(ComputerDTO computerDTO) throws InvalidDateValueException{
		Computer theComputer = new Computer();
		
		if (computerDTO.getId() != null) {
			theComputer.setId(Long.parseLong(computerDTO.getId()));
		}
		
		if (!computerDTO.getIntroducedDate().matches("\\\\d{4}-\\d{2}-\\d{2}")) {
			throw new InvalidDateValueException(computerDTO.getIntroducedDate());
		}
		if (!computerDTO.getDiscontinuedDate().matches("\\\\d{4}-\\d{2}-\\d{2}")) {
			throw new InvalidDateValueException(computerDTO.getDiscontinuedDate());
		}
		else {
			theComputer.setName(computerDTO.getName());
			theComputer.setIntroducedDate(LocalDate.parse(computerDTO.getIntroducedDate()));
			theComputer.setDiscontinuedDate(LocalDate.parse(computerDTO.getDiscontinuedDate()));
			Company company = companyDAO.findById(Long.parseLong(computerDTO.getCompanyDTO().getId()));

			theComputer.setCompany(company);
			
		}
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
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(Long.toString(computer.getCompany().getId()));
		theComputerDTO.setCompanyDTO(companyDTO);
		return theComputerDTO;
	}

}
