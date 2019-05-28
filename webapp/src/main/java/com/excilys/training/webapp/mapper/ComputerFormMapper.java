package com.excilys.training.webapp.mapper;

import static com.excilys.training.binding.validator.ValidatorUtils.isBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import org.springframework.stereotype.Component;

import com.excilys.training.binding.exception.CompanyNotFoundException;
import com.excilys.training.binding.mapper.CompanyMapper;
import com.excilys.training.core.Company;
import com.excilys.training.core.Computer;
import com.excilys.training.service.CompanyService;
import com.excilys.training.webapp.dto.ComputerDTOForm;

@Component
public class ComputerFormMapper {
	public CompanyMapper companyMapper;
	public CompanyService companyService;

	public ComputerFormMapper() {
	}
	
	public Computer dtoFormToModel(ComputerDTOForm computerDTOForm) {
		Computer theComputer = new Computer();

		if (!isBlank(computerDTOForm.getId())) {
			theComputer.setId(Long.parseLong(computerDTOForm.getId()));
		}

		try {
			theComputer.setName(computerDTOForm.getComputerName());
			if (!isBlank(computerDTOForm.getIntroduced())) {
				theComputer.setIntroducedDate(
						LocalDateTime.of(LocalDate.parse(computerDTOForm.getIntroduced()), LocalTime.of(12, 00)));
			}
			if (!isBlank(computerDTOForm.getDiscontinued())) {
				theComputer.setDiscontinuedDate(
						LocalDateTime.of(LocalDate.parse(computerDTOForm.getDiscontinued()), LocalTime.of(12, 00)));
			}
			if (!isBlank(computerDTOForm.getCompanyId())) {
				String companyId = computerDTOForm.getCompanyId();
				Company company = companyService.findById(Long.parseLong(companyId));
				if (company == null) {
					throw new CompanyNotFoundException(Long.parseLong(companyId));
				} else {
					theComputer.setCompany(company);
				}
			} else {
				theComputer.setCompany(new Company());
			}
		} catch (DateTimeParseException | CompanyNotFoundException e) {
			e.printStackTrace();
		}

		return theComputer;
	}
	
	public ComputerDTOForm modelToDtoForm(Computer computer) {
		ComputerDTOForm theComputerDTOForm = new ComputerDTOForm();
		theComputerDTOForm.setId(Long.toString(computer.getId()));
		theComputerDTOForm.setComputerName(computer.getName());
		if (computer.getIntroducedDate() != null) {
			theComputerDTOForm.setIntroduced(computer.getIntroducedDate().toLocalDate().toString());
		}
		if (computer.getDiscontinuedDate() != null) {
			theComputerDTOForm.setDiscontinued(computer.getDiscontinuedDate().toLocalDate().toString());
		}
		theComputerDTOForm.setCompanyName(computer.getCompany().getName());
		if (computer.getCompany().getId() != null) {
			theComputerDTOForm.setCompanyId(computer.getCompany().getId().toString());
		}
		return theComputerDTOForm;
	}
}