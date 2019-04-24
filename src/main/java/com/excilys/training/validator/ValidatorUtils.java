package com.excilys.training.validator;

import java.util.Objects;

public class ValidatorUtils {
    static boolean isBlank(String s) {
        return Objects.isNull(s) || s.trim().isEmpty();
    }
}
