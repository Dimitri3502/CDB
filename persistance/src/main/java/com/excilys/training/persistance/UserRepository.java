package com.excilys.training.persistance;

import org.springframework.data.repository.CrudRepository;

import com.excilys.training.core.User;


public interface UserRepository extends CrudRepository<User, Long> {

	User findByUsername(String email);
}
