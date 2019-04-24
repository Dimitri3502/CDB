package com.excilys.training.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.excilys.training.dto.CompanyDTO;
import com.excilys.training.dto.ComputerDTO;
import com.excilys.training.dto.ComputerDTOUi;

import static com.excilys.training.validator.ValidatorUtils.isBlank;

public class ComputerUiValidator extends Validator<ComputerDTOUi>{
    private static ComputerUiValidator instance;

    private ComputerUiValidator() {
    }

    public static ComputerUiValidator getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ComputerUiValidator();
        }
        return instance;
    }
   
    private static boolean checkDateFail(String date) {
        if (date==null) {
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
            errors.put("name", "Le nom ne peut pas être vide");
        }
        if (checkDateFail(toValidate.getIntroducedDate())) {
            errors.put("introduced", "introduced est mal formée");
        }
        if (checkDateFail(toValidate.getDiscontinuedDate())) {
            errors.put("discontinued", "discontinued est mal formée");
        }
        if (checkIdFail(toValidate.getCompanyId())) {
            errors.put("companyId", "l'id du fabriquant est mal formé");
        }
        return errors;
	}
}
