package com.excilys.training.validator;

import static com.excilys.training.validator.ValidatorUtils.isBlank;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.service.CompanyService;

@Component()
public class WebValidator extends Validator<ComputerDTO> {

	private final CompanyService companyService;

	public WebValidator(CompanyService companyService) {
		super();
		this.companyService = companyService;
	}

	private boolean checkDateFail(String date) {
		if (isBlank(date)) {
			return false;
		}
		try {
			LocalDate dateTocheck = LocalDate.parse(date);
			LocalDate minDate = LocalDate.of(1970,01,01);
			LocalDate maxDate = LocalDate.of(2038,01,01);
			if (dateTocheck.isAfter(minDate) && dateTocheck.isBefore(maxDate)) {
				return false;
			} else {
				return true;
			}
			
		} catch (DateTimeParseException e) {
			return true;
		}
	}

	private boolean checkIdFail(String id) {
		if (id==null) {
			return false;
		}
		try {
			Long companyId = Long.valueOf(id);
			return !companyService.isPresent(companyId);
		} catch (NumberFormatException e) {
			return true;
		}
	}

	@Override
	protected Map<String, String> validation(ComputerDTO toValidate) {
		final HashMap<String, String> errors = new HashMap<>();
		if (isBlank(toValidate.getName())) {
			errors.put("computerName", "Le nom ne peut pas être vide");
		}
		if (checkDateFail(toValidate.getIntroducedDate())) {
			errors.put("introduced", "La date d'introduction doit être entre 1970-01-01 et 2038-01-19");
		}
		if (checkDateFail(toValidate.getDiscontinuedDate())) {
			errors.put("discontinued", "La date d'expiration doit être entre 1970-01-01 et 2038-01-19");
		}
		if (checkIdFail(toValidate.getCompanyDTO().getId())) {
			errors.put("companyId", "Ce fabriquant n'hexiste pas");
		}
		return errors;
	}
}
