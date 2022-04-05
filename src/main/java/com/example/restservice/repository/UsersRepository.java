package com.example.restservice.repository;

import java.util.*;

import org.springframework.data.repository.CrudRepository;

import com.example.restservice.model.Users;

// This will be AUTO IMPLEMENTED by Spring into a Bean called usersRepository
// CRUD refers Create, Read, Update, Delete

public interface UsersRepository extends CrudRepository<Users, Long> {
	List<Users> findByUsername(String username);
}