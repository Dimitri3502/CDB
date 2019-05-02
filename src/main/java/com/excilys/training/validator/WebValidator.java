package com.excilys.training.validator;

import static com.excilys.training.validator.ValidatorUtils.isBlank;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.service.CompanyService;

public class WebValidator extends Validator<ComputerDTO> {
	private static WebValidator instance;
	private static final CompanyService companyService = CompanyService.getInstance();
	private WebValidator() {
	    }

	public static WebValidator getInstance() {
		if (Objects.isNull(instance)) {
			instance = new WebValidator();
		}
		return instance;
	}

	private static boolean checkDateFail(String date) {
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

	private static boolean checkIdFail(String id) {
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
