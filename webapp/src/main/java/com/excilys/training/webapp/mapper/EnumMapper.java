package com.excilys.training.webapp.mapper;

import org.springframework.stereotype.Component;

import com.excilys.training.binding.pagination.ENUMS.OrderByChamp;
import com.excilys.training.binding.pagination.ENUMS.OrderByDirection;

@Component
public class EnumMapper {

	public String map(OrderByDirection c) {
		switch (c) {
		default:
		case ASC:
			return "ASC";
		case DESC:
			return "DESC";
		}
	}

	public String map(OrderByChamp c) {
		switch (c) {
		default:
		case ID:
			return "id";
		case NAME:
			return "name";
		case INTRODUCED:
			return "introduced";
		case DISCONTINUED:
			return "discontinued";
		case COMPANY:
			return "company.name";
		}
	}
}
