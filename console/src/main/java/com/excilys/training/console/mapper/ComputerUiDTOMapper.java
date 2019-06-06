package com.excilys.training.console.mapper;

import static com.excilys.training.binding.validator.ValidatorUtils.isBlank;

import org.springframework.stereotype.Component;

import com.excilys.training.binding.dto.CompanyDTO;
import com.excilys.training.binding.dto.ComputerDTO;
import com.excilys.training.console.dto.ComputerDTOUi;

@Component
public class ComputerUiDTOMapper {


	public ComputerDTO uiToDTO(ComputerDTOUi computerDTOUi) {
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setName(computerDTOUi.getName());
		if (!isBlank(computerDTOUi.getIntroducedDate())) {
			computerDTO.setIntroduced(computerDTOUi.getIntroducedDate().replace('/', '-'));
		}
		if (!isBlank(computerDTOUi.getIntroducedDate())) {
			computerDTO.setDiscontinued(computerDTOUi.getDiscontinuedDate().replace('/', '-'));
		}
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(Long.parseLong(computerDTOUi.getCompanyId()));
		computerDTO.setCompanyDTO(companyDTO);
		return computerDTO;

	}
}
