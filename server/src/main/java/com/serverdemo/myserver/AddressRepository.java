package com.serverdemo.myserver;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Component
public interface AddressRepository extends CrudRepository<Address, Integer> { }