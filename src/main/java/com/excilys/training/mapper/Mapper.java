package com.excilys.training.mapper;

import com.excilys.training.dto.Dto;
import com.excilys.training.exception.InvalidDateValueException;
import com.excilys.training.exception.InvalidDiscontinuedDate;
import com.excilys.training.exception.NotFoundException;
import com.excilys.training.model.Model;

public abstract class Mapper<T extends Dto, U extends Model> {

	public Mapper() {}
	
	public abstract U dtoToModel (T dtoObject) throws InvalidDateValueException, InvalidDiscontinuedDate;
	public abstract T modelToDto (U modelObject);
	
	public int idToInt(String id) {
		return Integer.parseInt(id);
	}
}
