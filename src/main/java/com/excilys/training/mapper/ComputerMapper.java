package com.excilys.training.mapper;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.CompanyNotFoundException;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.model.Company;
import com.excilys.training.model.Computer;
import com.excilys.training.persistance.CompanyDAO;
import com.excilys.training.persistance.Dao;
import com.excilys.training.service.ComputerService;
import static com.excilys.training.validator.ValidatorUtils.isBlank;

public class ComputerMapper extends Mapper<ComputerDTO, Computer> {
	private final static CompanyMapper companyMapper = CompanyMapper.getInstance();

	private static ComputerMapper instance = null;

	private CompanyDAO companyDAO;

	private ComputerMapper(CompanyDAO companyDAO) {
		super();
		this.companyDAO = companyDAO;
	}

	public final static ComputerMapper getInstance(CompanyDAO companyDAO) {
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

		if (!isBlank(computerDTO.getId())) {
			theComputer.setId(Long.parseLong(computerDTO.getId()));
		}

		try {
			theComputer.setName(computerDTO.getName());
			if (!isBlank(computerDTO.getIntroducedDate())) {
				theComputer.setIntroducedDate(
						LocalDateTime.of(LocalDate.parse(computerDTO.getIntroducedDate()), LocalTime.of(12, 00)));
			}
			if (!isBlank(computerDTO.getDiscontinuedDate())) {
				theComputer.setDiscontinuedDate(
						LocalDateTime.of(LocalDate.parse(computerDTO.getDiscontinuedDate()), LocalTime.of(12, 00)));
			}
			if (!isBlank(computerDTO.getCompanyDTO().getId())) {
				String companyId = computerDTO.getCompanyDTO().getId();
				Optional<Company> company = companyDAO.findById(Long.parseLong(companyId));
				if (!company.isPresent()) {
					throw new CompanyNotFoundException(Long.parseLong(companyId));
				} else {
					theComputer.setCompany(company.get());
				}
			}
			else {
				theComputer.setCompany(new Company());
			}
		} catch (DateTimeParseException | CompanyNotFoundException e) {
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
		CompanyDTO companyDTO = companyMapper.modelToDto(computer.getCompany());
		theComputerDTO.setCompanyDTO(companyDTO);
		return theComputerDTO;
	}

}
