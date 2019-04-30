package com.excilys.training.validator;

import static com.excilys.training.validator.ValidatorUtils.isBlank;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.excilys.training.dto.ComputerDTOUi;

public class WebValidator extends Validator<ComputerDTOUi> {
	private static WebValidator instance;

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
			LocalDate.parse(date);
			return false;
		} catch (DateTimeParseException e) {
			return true;
		}
	}

	private static boolean checkIdFail(String id) {
		if (id==null) {
			return false;
		}
		try {
			Long.valueOf(id);
			return false;
		} catch (NumberFormatException e) {
			return true;
		}
	}

	@Override
	protected Map<String, String> validation(ComputerDTOUi toValidate) {
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
		if (checkIdFail(toValidate.getCompanyId())) {
			errors.put("companyId", "l'id du fabriquant est mal formé");
		}
		return errors;
	}
}
