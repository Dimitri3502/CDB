package com.excilys.training.dao;

import java.sql.Connection;
import java.util.Collection;
import java.util.List;

import com.excilys.training.model.Computer;

public abstract class Dao<T> {
   
  /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
  public abstract long create(T obj);

  /**
  * Méthode pour effacer
  * @param obj
  * @return boolean 
  */
  public abstract boolean delete(T obj);

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract boolean update(T obj);

  /**
  * Méthode de recherche des informations
  * @param key
  * @return T
  */
  public abstract T findById(long id);

  /**
  * Méthode de recherche des informations
  * @return List<T>
  */
  public abstract Collection<T> getAll();


  }
