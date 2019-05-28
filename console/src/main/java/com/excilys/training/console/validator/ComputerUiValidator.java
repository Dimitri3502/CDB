package com.excilys.training.console.validator;

import static com.excilys.training.binding.validator.ValidatorUtils.isBlank;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.excilys.training.binding.validator.Validator;
import com.excilys.training.console.dto.ComputerDTOUi;

@Component
public class ComputerUiValidator extends Validator<ComputerDTOUi>{
   
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
            errors.put("introduced", "La date d'introduction de l'ordinateur est mal formée");
        }
        if (checkDateFail(toValidate.getDiscontinuedDate())) {
            errors.put("discontinued", "La date d'expiration de l'ordinateur est mal formée");
        }
        if (checkIdFail(toValidate.getCompanyId())) {
            errors.put("companyId", "l'id du fabriquant est mal formé");
        }
        return errors;
	}
}
