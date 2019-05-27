package com.excilys.training.validator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.service.CompanyService;

import static com.excilys.training.validator.ValidatorUtils.isBlank;

@Component()
public class ComputerDTOValidator extends Validator<ComputerDTO> {


	private final CompanyService companyService;

	public ComputerDTOValidator(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}



	@Override
	protected Map<String, String> validation(ComputerDTO toValidate) {

		HashMap<String, String> errors = new HashMap<>();
		if (isBlank(toValidate.getName())) {
			errors.put("name", "Le nom ne peux pas être nul ou vide.");
		}

		if (Objects.nonNull(toValidate.getIntroducedDate()) && (Objects.nonNull(toValidate.getDiscontinuedDate()))
				&& LocalDate.parse(toValidate.getIntroducedDate())
						.isAfter(LocalDate.parse(toValidate.getDiscontinuedDate()))) {
			errors.put("discontinued", "La date d'expiration ne peux pas être avant la date d'introduction.");
		}

		final CompanyDTO companyDTO = toValidate.getCompanyDTO();

		if (Objects.nonNull(companyDTO.getId()) && companyService.findById(Long.parseLong(companyDTO.getId()))==null) {
			errors.put("companyId", "Le fabriquant avec l'id " + companyDTO.getId() + " n'existe pas");
		}

		return errors;
	}

}
