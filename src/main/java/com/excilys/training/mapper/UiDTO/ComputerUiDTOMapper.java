package com.excilys.training.mapper.UiDTO;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.dto.ComputerDTOUi;

public class ComputerUiDTOMapper {
	private static ComputerUiDTOMapper instance = null;

	
	private ComputerUiDTOMapper() {
	}

	public final static ComputerUiDTOMapper getInstance()  {
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
		computerDTO.setIntroducedDate(computerDTOUi.getIntroducedDate());
		computerDTO.setDiscontinuedDate(computerDTOUi.getDiscontinuedDate());
		CompanyDTO companyDTO = new CompanyDTO(computerDTOUi.getCompanyId(),null);
		computerDTO.setCompanyDTO(companyDTO);
		return computerDTO;
		
	}
}
