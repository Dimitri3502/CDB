package com.excilys.training.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class DateValidator implements ConstraintValidator<Antes, Object> {

	private String introduced;
	private String discontinued;
    
	@Override
	public void initialize(final Antes constraintAnnotation) {
		introduced = constraintAnnotation.first();
		discontinued = constraintAnnotation.second();
	}

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
        try
        {
            final String firstObj = BeanUtils.getProperty(value, introduced);
            final String secondObj = BeanUtils.getProperty(value, discontinued);
            if (firstObj != null && secondObj != null) {
            	return LocalDate.parse(secondObj).isAfter(LocalDate.parse(firstObj));
            }
            
           
        }
        catch (final Exception ignore)
        {
            // ignore
        }
        return true;
    }
}
