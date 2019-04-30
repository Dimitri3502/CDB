package com.excilys.training.mapper.UiDTO;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.dto.ComputerDTOUi;
import static com.excilys.training.validator.ValidatorUtils.isBlank;

public class ComputerUiDTOMapper {
	private static ComputerUiDTOMapper instance = null;

	private ComputerUiDTOMapper() {
	}

	public final static ComputerUiDTOMapper getInstance() {
		if (ComputerUiDTOMapper.instance == null) {

			if (ComputerUiDTOMapper.instance == null) {
				ComputerUiDTOMapper.instance = new ComputerUiDTOMapper();
			}

		}
		return ComputerUiDTOMapper.instance;
	}

	public ComputerDTO uiToDTO(ComputerDTOUi computerDTOUi) {
		ComputerDTO computerDTO = new ComputerDTO();
		computerDTO.setName(computerDTOUi.getName());
		if (!isBlank(computerDTOUi.getIntroducedDate())) {
			computerDTO.setIntroducedDate(computerDTOUi.getIntroducedDate().replace('/', '-'));
		}
		if (!isBlank(computerDTOUi.getIntroducedDate())) {
			computerDTO.setDiscontinuedDate(computerDTOUi.getDiscontinuedDate().replace('/', '-'));
		}
		CompanyDTO companyDTO = new CompanyDTO();
		companyDTO.setId(computerDTOUi.getCompanyId());
		computerDTO.setCompanyDTO(companyDTO);
		return computerDTO;

	}
}
