package com.excilys.training.binding.mapper;

import java.util.List;

import com.excilys.training.binding.dto.Dto;
import com.excilys.training.binding.exception.InvalidDateValueException;
import com.excilys.training.binding.exception.InvalidDiscontinuedDate;
import com.excilys.training.core.Model;
import com.excilys.training.persistance.exception.NotFoundException;

public abstract class Mapper<T extends Dto, U extends Model> {

	public Mapper() {}
	
	public abstract U dtoToModel (T dtoObject) throws InvalidDateValueException, InvalidDiscontinuedDate;
	public abstract T modelToDto (U modelObject);
	public abstract List<T> allModelToDTO (List<U> modelObject);
	
	public int idToInt(String id) {
		return Integer.parseInt(id);
	}
}
