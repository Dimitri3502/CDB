package com.excilys.training.persistance;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import com.excilys.training.binding.exception.CompanyNotFoundException;
import com.excilys.training.binding.exception.InvalidDiscontinuedDate;
import com.excilys.training.binding.exception.NotFoundException;

public abstract class Dao<T> {

	/**
	 * Méthode de création
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract long create(T obj);

	/**
	 * Méthode pour effacer
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean delete(T obj);

	/**
	 * Méthode de mise à jour
	 * 
	 * @param obj
	 * @return boolean
	 */
	public abstract boolean update(T obj);

	/**
	 * Méthode de recherche des informations
	 * 
	 * @param key
	 * @return T
	 * @throws CompanyNotFoundException 
	 * @throws NotFoundException
	 * @throws InvalidDiscontinuedDate
	 */
	public abstract T findById(long id) throws NotFoundException;

	/**
	 * Méthode de recherche des informations
	 * 
	 * @return List<T>
	 * @throws InvalidDiscontinuedDate
	 */
	public abstract List<T> getAll();

	public abstract List<T> getAll(int limit, int offset);

	public abstract boolean delete(Long id);
}
