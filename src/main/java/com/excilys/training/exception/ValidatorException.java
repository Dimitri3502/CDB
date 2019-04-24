package com.excilys.training.exception;

import com.excilys.training.validator.Validator;

public class ValidatorException extends Exception {
	    private final Validator.Result result;

	    public ValidatorException(Validator.Result result) {
	        super(result.values().toString());
	        this.result = result;
	    }

	    public Validator.Result getResult() {
	        return result;
	    }
}
