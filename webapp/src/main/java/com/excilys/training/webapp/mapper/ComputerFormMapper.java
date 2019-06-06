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
import com.excilys.training.service.ICompanyService;
import com.excilys.training.webapp.dto.ComputerDTOForm;

@Component
public class ComputerFormMapper {
	public final CompanyMapper companyMapper;
	public final ICompanyService companyService;

	public ComputerFormMapper(CompanyMapper companyMapper, ICompanyService companyService) {
		this.companyMapper = companyMapper;
		this.companyService = companyService;
	}

	public Computer dtoFormToModel(ComputerDTOForm computerDTOForm) {
		Computer theComputer = new Computer();

		theComputer.setId(computerDTOForm.getId());

		try {
			theComputer.setName(computerDTOForm.getComputerName());
			if (!isBlank(computerDTOForm.getIntroduced())) {
				theComputer.setIntroduced(
						LocalDateTime.of(LocalDate.parse(computerDTOForm.getIntroduced()), LocalTime.of(12, 00)));
			}
			if (!isBlank(computerDTOForm.getDiscontinued())) {
				theComputer.setDiscontinued(
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
			}
		} catch (DateTimeParseException | CompanyNotFoundException e) {
			e.printStackTrace();
		}

		return theComputer;
	}

	public ComputerDTOForm modelToDtoForm(Computer computer) {
		ComputerDTOForm theComputerDTOForm = new ComputerDTOForm();
		theComputerDTOForm.setId(computer.getId());
		theComputerDTOForm.setComputerName(computer.getName());
		if (computer.getIntroduced() != null) {
			theComputerDTOForm.setIntroduced(computer.getIntroduced().toLocalDate().toString());
		}
		if (computer.getDiscontinued() != null) {
			theComputerDTOForm.setDiscontinued(computer.getDiscontinued().toLocalDate().toString());
		}
		if (computer.getCompany() != null) {
			if (computer.getCompany().getName() != null) {
				theComputerDTOForm.setCompanyName(computer.getCompany().getName());
			}
			if (computer.getCompany().getId() != null) {
				theComputerDTOForm.setCompanyId(computer.getCompany().getId().toString());
			}
		}
		return theComputerDTOForm;
	}
}