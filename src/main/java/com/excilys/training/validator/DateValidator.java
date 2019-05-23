package com.excilys.training.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.excilys.training.dto.ComputerDTOForm;

public class DateValidator implements ConstraintValidator<Antes, ComputerDTOForm> {

	@Override
	public boolean isValid(final ComputerDTOForm value, final ConstraintValidatorContext context) {
		try {
			final String firstObj = value.getIntroduced();
			final String secondObj = value.getDiscontinued();
			if (firstObj != null && secondObj != null) {
				return LocalDate.parse(secondObj).isAfter(LocalDate.parse(firstObj));
			} else {
				return true;
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
