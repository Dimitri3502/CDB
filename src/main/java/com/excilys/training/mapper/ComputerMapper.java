package com.excilys.training.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import com.excilys.training.dao.CompanyDAO;
import com.excilys.training.dao.Dao;
import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.CompanyNotFoundException;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.service.ComputerService;

public class ComputerMapper extends Mapper<ComputerDTO, Computer> {

	private static ComputerMapper instance = null;
	
	private CompanyDAO companyDAO;
	
	private ComputerMapper(CompanyDAO companyDAO) {
		super();
		this.companyDAO = companyDAO;
	}

	public final static ComputerMapper getInstance(CompanyDAO companyDAO)  {
		if (ComputerMapper.instance == null) {
             
              if (ComputerMapper.instance == null) {
            	  ComputerMapper.instance = new ComputerMapper(companyDAO);
              }
            
         }
         return ComputerMapper.instance;
	}

	

	@Override
	public Computer dtoToModel(ComputerDTO computerDTO) {
		Computer theComputer = new Computer();
		
		if (computerDTO.getId() != null) {
			theComputer.setId(Long.parseLong(computerDTO.getId()));
		}
		
        try {
        	theComputer.setName(computerDTO.getName());
			theComputer.setIntroducedDate(LocalDateTime.of(LocalDate.parse(computerDTO.getIntroducedDate()),LocalTime.of(12, 00)));
			theComputer.setDiscontinuedDate(LocalDateTime.of(LocalDate.parse(computerDTO.getDiscontinuedDate()),LocalTime.of(12, 00)));
			String companyId = computerDTO.getCompanyDTO().getId();
			if (companyId != null) {
				Optional<Company> company = companyDAO.findById(Long.parseLong(companyId));
				if (!company.isPresent()) {
					throw new CompanyNotFoundException(Long.parseLong(companyId));
				}else {
					theComputer.setCompany(company.get());
				}
			}
        } catch (DateTimeParseException| CompanyNotFoundException e) {
            e.printStackTrace();
        }
        
		return theComputer;
	}

	@Override
	public ComputerDTO modelToDto(Computer computer) {
		ComputerDTO theComputerDTO = new ComputerDTO();
		theComputerDTO.setId(Long.toString(computer.getId()));
		theComputerDTO.setName(computer.getName());
		if (computer.getIntroducedDate() != null) {
			theComputerDTO.setIntroducedDate(computer.getIntroducedDate().toLocalDate().toString());
		}
		if (computer.getDiscontinuedDate() != null) {
			theComputerDTO.setDiscontinuedDate(computer.getDiscontinuedDate().toLocalDate().toString());
		}
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(Long.toString(computer.getCompany().getId()));
		companyDTO.setName(computer.getCompany().getName());
		theComputerDTO.setCompanyDTO(companyDTO);
		return theComputerDTO;
	}

}
