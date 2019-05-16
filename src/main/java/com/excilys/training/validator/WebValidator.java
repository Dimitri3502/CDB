package com.excilys.training.validator;

import static com.excilys.training.validator.ValidatorUtils.isBlank;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Component;

import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.exception.CompanyNotFoundException;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.service.CompanyService;

import static com.excilys.training.servlets.CONSTANTES.*;

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
			LocalDate minDate = LocalDate.of(1970, 01, 01);
			LocalDate maxDate = LocalDate.of(2038, 01, 01);
			if (dateTocheck.isAfter(minDate) && dateTocheck.isBefore(maxDate)) {
				return false;
			} else {
				return true;
			}

		} catch (DateTimeParseException e) {
			throw new InvalidDateValueException(date);
		}
	}

	private boolean checkIdFail(String id) {
		if (id == null) {
			return false;
		}
		try {
			Long companyId = Long.valueOf(id);
			if (!companyService.isPresent(companyId)) {
				throw new CompanyNotFoundException(id);
			}
			;
			return false;
		} catch (NumberFormatException e) {
			throw new CompanyNotFoundException(id);
		}
	}

	@Override
	protected Map<String, String> validation(ComputerDTO toValidate) {
		final HashMap<String, String> errors = new HashMap<>();
		if (isBlank(toValidate.getName())) {
			errors.put(CHAMP_COMPUTERNAME, "Le nom ne peut pas être vide");
		}
		
		try {
			checkDateFail(toValidate.getIntroducedDate());
		} catch (InvalidDateValueException e) {
			errors.put(CHAMP_INTRODUCED, e.getMessage());
		}
		
		try {
			checkDateFail(toValidate.getDiscontinuedDate());
		} catch (InvalidDateValueException e) {
			errors.put(CHAMP_DISCONTINUED, e.getMessage());
		}

		try {
			checkIdFail(toValidate.getCompanyDTO().getId());
		} catch (CompanyNotFoundException e) {
			errors.put(CHAMP_COMPANYID, e.getMessage());
		}
		return errors;
	}
}
