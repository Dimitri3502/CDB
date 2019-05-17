package com.excilys.training.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

public class DateValidator implements ConstraintValidator<Antes, Object> {

	private String introducedDate;
	private String discontinuedDate;
    
	@Override
	public void initialize(final Antes constraintAnnotation) {
		introducedDate = constraintAnnotation.first();
		discontinuedDate = constraintAnnotation.second();
	}

    @Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context)
    {
        try
        {
            final String firstObj = BeanUtils.getProperty(value, introducedDate);
            final String secondObj = BeanUtils.getProperty(value, discontinuedDate);
            if (firstObj != null && secondObj != null) {
            	return LocalDate.parse(firstObj).isAfter(LocalDate.parse(secondObj));
            }
            
           
        }
        catch (final Exception ignore)
        {
            // ignore
        }
        return true;
    }
}
