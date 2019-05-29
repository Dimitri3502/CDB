package com.excilys.training.binding.mapper;

import java.util.List;

import com.excilys.training.binding.dto.Dto;
import com.excilys.training.binding.exception.InvalidDateValueException;
import com.excilys.training.binding.exception.InvalidDiscontinuedDate;

public abstract class Mapper<T extends Dto, U > {

	public Mapper() {}
	
	public abstract U dtoToModel (T dtoObject) throws InvalidDateValueException, InvalidDiscontinuedDate;
	public abstract T modelToDto (U modelObject);
	public abstract List<T> allModelToDTO (List<U> modelObject);
	
	public int idToInt(String id) {
		return Integer.parseInt(id);
	}
}
