package com.excilys.training.console.validator;

import java.util.Objects;

public class ValidatorUtils {
    public static boolean isBlank(String s) {
        return Objects.isNull(s) || s.trim().isEmpty();
    }
}
