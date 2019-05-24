package com.excilys.training.converter;

import static com.excilys.training.controller.web.CONSTANTES.CHAMP_COMPANY_NAME;
import static com.excilys.training.controller.web.CONSTANTES.CHAMP_COMPUTERNAME;
import static com.excilys.training.controller.web.CONSTANTES.CHAMP_DISCONTINUED;
import static com.excilys.training.controller.web.CONSTANTES.CHAMP_ID;
import static com.excilys.training.controller.web.CONSTANTES.CHAMP_INTRODUCED;

import org.springframework.core.convert.converter.Converter;

import com.excilys.training.pagination.ENUMS.OrderByChamp;

public class StringToOrderByChampConverter implements Converter<String, OrderByChamp> {

    public OrderByChamp convert(String s) {
		if (s == null) {
			return OrderByChamp.ID;
		} else {
			switch (s) {
			default:
			case CHAMP_ID:
				return OrderByChamp.ID;
			case CHAMP_COMPUTERNAME:
				return OrderByChamp.NAME;
			case CHAMP_INTRODUCED:
				return OrderByChamp.INTRODUCED;
			case CHAMP_DISCONTINUED:
				return OrderByChamp.DISCONTINUED;
			case CHAMP_COMPANY_NAME:
				return OrderByChamp.COMPANY;
			}
		}
	}
}