package com.example.restservice.repository;

import java.util.*;

import org.springframework.data.repository.CrudRepository;

import com.example.restservice.model.Stock;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface StockRepository extends CrudRepository<Stock, Long> {
}