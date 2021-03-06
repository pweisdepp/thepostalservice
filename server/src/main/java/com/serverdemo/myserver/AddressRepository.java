package com.serverdemo.myserver;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

@Component
public interface AddressRepository extends CrudRepository<Address, Integer> {
    @Query("select id, country from addresses a where a.country=:countryCode")
    public Iterable<Address> findByCountry(@Param("countryCode") String countryCode);
}