package com.excilys.training.validator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.service.CompanyService;

import static com.excilys.training.validator.ValidatorUtils.isBlank;

public class ComputerDTOValidator extends Validator<ComputerDTO> {

	private static ComputerDTOValidator instance;
	private final CompanyService companyService = CompanyService.getInstance();

	private ComputerDTOValidator() {
	}

	public static ComputerDTOValidator getInstance() {
		if (Objects.isNull(instance)) {
			instance = new ComputerDTOValidator();
		}
		return instance;
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

		if (Objects.nonNull(companyDTO.getId()) && companyService.findById(Long.parseLong(companyDTO.getId())).isEmpty()) {
			errors.put("companyId", "Le fabriquant avec l'id " + companyDTO.getId() + " n'existe pas");
		}

		return errors;
	}

}
