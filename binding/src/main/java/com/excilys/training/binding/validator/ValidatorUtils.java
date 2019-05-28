package com.excilys.training.binding.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

public class ValidatorUtils {
    public static boolean isBlank(String s) {
        return Objects.isNull(s) || s.trim().isEmpty();
    }
	public static LocalDateTime Parse( String s) {
		return LocalDateTime.of(LocalDate.parse(s), LocalTime.of(2, 0));
	}
}
