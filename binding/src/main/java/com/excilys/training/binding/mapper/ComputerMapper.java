package com.excilys.training.binding.mapper;

import static com.excilys.training.binding.validator.ValidatorUtils.isBlank;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.excilys.training.binding.dto.CompanyDTO;
import com.excilys.training.binding.dto.ComputerDTO;
import com.excilys.training.binding.exception.CompanyNotFoundException;
import com.excilys.training.core.Company;
import com.excilys.training.core.Computer;

@Component
public class ComputerMapper extends Mapper<ComputerDTO, Computer> {
	private final CompanyMapper companyMapper;
	private final FindCompanyById companyService;

	public ComputerMapper(CompanyMapper companyMapper, FindCompanyById companyService) {
		super();
		this.companyMapper = companyMapper;
		this.companyService = companyService;
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

	@Override
	public List<ComputerDTO> allModelToDTO(List<Computer> theComputerList) {
		List<ComputerDTO> theComputerDtoList = (List<ComputerDTO>) theComputerList.stream().map(s -> modelToDto(s))
				.collect(Collectors.toList());
		return theComputerDtoList;

	}

}
