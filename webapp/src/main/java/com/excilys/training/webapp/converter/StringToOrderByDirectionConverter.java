package com.excilys.training.webapp.converter;

import org.springframework.core.convert.converter.Converter;

import com.excilys.training.binding.pagination.ENUMS.OrderByDirection;

public class StringToOrderByDirectionConverter implements Converter<String, OrderByDirection> {

    public OrderByDirection convert(String s) {
    		if (s == null) {
    			return OrderByDirection.ASC;
    		} else {
    			switch (s) {
    			default:
    			case "asc":
    				return OrderByDirection.ASC;
    			case "desc":
    				return OrderByDirection.DESC;
    			}
    		}
    	}
}